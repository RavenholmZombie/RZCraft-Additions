package com.gvarchives.rzadditions.content.block.machines;

import com.gvarchives.rzadditions.core.ModSounds;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DoorChimeBlock extends HorizontalDirectionalBlock implements IWrenchable
{
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final VoxelShape SHAPE_NORTH = Block.box(6.75, 0.75, 15, 9.25, 3.25, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.box(6.75, 0.75, 0, 9.25, 3.25, 1);
    private static final VoxelShape SHAPE_WEST  = Block.box(15, 0.75, 6.75, 16, 3.25, 9.25);
    private static final VoxelShape SHAPE_EAST  = Block.box(0, 0.75, 6.75, 1, 3.25, 9.25);
    public DoorChimeBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false));

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
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        boolean powered = context.getLevel().hasNeighborSignal(context.getClickedPos());

        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(POWERED, powered);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
    {
        if (level.isClientSide)
            return;

        boolean poweredNow = level.hasNeighborSignal(pos);
        boolean wasPowered = state.getValue(POWERED);
        float pitch = 0.95F + (level.random.nextFloat() * 0.10F);

        if (poweredNow != wasPowered)
        {
            level.setBlock(pos, state.setValue(POWERED, poweredNow), 3);

            if (poweredNow)
            {
                level.playSound(
                        null,
                        pos,
                        ModSounds.CHIME.get(),
                        SoundSource.BLOCKS,
                        1.0F,
                        pitch
                );
                if (level instanceof ServerLevel serverLevel)
                {
                    serverLevel.sendParticles(
                            ParticleTypes.NOTE,
                            pos.getX() + 0.5D,
                            pos.getY() + 0.75D,
                            pos.getZ() + 0.5D,
                            1,
                            0.0D,
                            0.1D,
                            0.0D,
                            1.0D
                    );
                }
            }
        }
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
