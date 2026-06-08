package com.gvarchives.rzadditions.content.block.machines;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

            if(!level.isClientSide)
            {
                level.playSound(null, pos, SoundEvents.UI_BUTTON_CLICK.get(), SoundSource.BLOCKS, 1.0F, 3.0F);
            }

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
        AABB frontBox = detectionBox(pos, facing);
        AABB backBox = detectionBox(pos, facing.getOpposite());

        return !level.getEntitiesOfClass(Player.class, frontBox).isEmpty()
                || !level.getEntitiesOfClass(Player.class, backBox).isEmpty();
    }

    private AABB detectionBox(BlockPos pos, Direction dir)
    {
        BlockPos first = pos.relative(dir);
        BlockPos second = pos.relative(dir, 2);

        return new AABB(first).minmax(new AABB(second))
                .inflate(0.35D, 1.5D, 0.35D)
                .move(0, -1.5D, 0);
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
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(POWERED, false);
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
