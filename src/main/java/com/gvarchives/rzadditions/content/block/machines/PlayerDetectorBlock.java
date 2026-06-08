package com.gvarchives.rzadditions.content.block.machines;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.PushReaction;

public class PlayerDetectorBlock extends HorizontalDirectionalBlock implements IWrenchable
{
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final VoxelShape SHAPE_NORTH = Block.box(2, 0, 14, 14, 2, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.box(2, 0, 0, 14, 2, 2);
    private static final VoxelShape SHAPE_WEST  = Block.box(14, 0, 2, 16, 2, 14);
    private static final VoxelShape SHAPE_EAST  = Block.box(0, 0, 2, 2, 2, 14);
    public PlayerDetectorBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false));

    }

    private BlockPos getAttachedBlockPos(BlockPos pos)
    {
        return pos.below();
    }

    private Direction getAttachedDirection(BlockState state)
    {
        return state.getValue(FACING).getOpposite();
    }

    private BlockPos getSupportPos(BlockPos pos, BlockState state)
    {
        return pos.relative(getAttachedDirection(state));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return switch (state.getValue(FACING))
        {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        boolean detected = detectsPlayer(level, pos, state.getValue(FACING));

        if (state.getValue(POWERED) != detected)
        {
            BlockState newState = state.setValue(POWERED, detected);
            level.setBlock(pos, newState, 3);
            updateDoorwayNeighbors(level, pos, state.getValue(FACING));

            BlockPos attached = getAttachedBlockPos(pos);
            BlockPos belowAttached = attached.below();

            level.updateNeighborsAt(pos, this);
            level.updateNeighborsAt(attached, this);
            level.updateNeighborsAt(belowAttached, this);
            level.updateNeighborsAt(pos.above(), this);

            level.updateNeighborsAt(pos.relative(state.getValue(FACING)), this);
            level.updateNeighborsAt(pos.relative(state.getValue(FACING)).below(), this);
            level.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
            level.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()).below(), this);

            if(!level.isClientSide)
            {
                level.playSound(null, pos, SoundEvents.UI_BUTTON_CLICK.get(), SoundSource.BLOCKS, 1.0F, 3.0F);
            }
        }

        level.scheduleTick(pos, this, 5);
    }

    private void updateDoorwayNeighbors(ServerLevel level, BlockPos pos, Direction facing)
    {
        BlockPos attached = pos.below();

        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(attached, this);

        level.updateNeighborsAt(attached.below(), this);
        level.updateNeighborsAt(attached.relative(facing), this);
        level.updateNeighborsAt(attached.relative(facing.getOpposite()), this);
        level.updateNeighborsAt(attached.below().relative(facing), this);
        level.updateNeighborsAt(attached.below().relative(facing.getOpposite()), this);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean moved)
    {
        if (!level.isClientSide)
        {
            level.scheduleTick(pos, this, 5);
        }
    }

    private boolean detectsPlayer(ServerLevel level, BlockPos pos, Direction facing)
    {
        AABB frontBox = frontDetectionBox(pos, facing);
        AABB backBox = backDetectionBox(pos, facing);

        return !level.getEntitiesOfClass(Player.class, frontBox).isEmpty()
                || !level.getEntitiesOfClass(Player.class, backBox).isEmpty();
    }

    private AABB frontDetectionBox(BlockPos pos, Direction dir)
    {
        return new AABB(pos)
                .inflate(0.35D, 1.0D, 0.35D)
                .move(
                        dir.getStepX() * 0.05D,
                        -1.0D,
                        dir.getStepZ() * 0.05D
                );
    }

    private AABB backDetectionBox(BlockPos pos, Direction dir)
    {
        return new AABB(pos)
                .inflate(0.35D, 1.0D, 0.35D)
                .move(
                        -dir.getStepX() * 1D,
                        -1.0D,
                        -dir.getStepZ() * 1D
                );
    }

    @Override
    public boolean isSignalSource(BlockState state)
    {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Direction clickedFace = context.getClickedFace();

        if (clickedFace.getAxis().isVertical())
            return null;

        Direction facing = clickedFace;

        BlockPos pos = context.getClickedPos();
        BlockPos supportPos = pos.relative(facing.getOpposite());

        if (!context.getLevel().getBlockState(supportPos).isFaceSturdy(
                context.getLevel(),
                supportPos,
                facing
        ))
            return null;

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(POWERED, false);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockPos supportPos = getSupportPos(pos, state);
        Direction supportFace = state.getValue(FACING);

        return level.getBlockState(supportPos).isFaceSturdy(level, supportPos, supportFace);
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor level,
            BlockPos pos,
            BlockPos neighborPos
    )
    {
        if (direction == getAttachedDirection(state) && !state.canSurvive(level, pos))
            return Blocks.AIR.defaultBlockState();

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state)
    {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, POWERED);
    }

    ///
    /// Create integration - Wrench
    ///
    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (level.isClientSide)
            return InteractionResult.SUCCESS;

        if (context.getPlayer() instanceof ServerPlayer player)
        {
            ItemStack stack = new ItemStack(this.asItem());

            level.levelEvent(2001, pos, Block.getId(state));
            level.removeBlock(pos, false);

            if (!player.getInventory().add(stack))
                player.drop(stack, false);
        }

        return InteractionResult.SUCCESS;
    }
}
