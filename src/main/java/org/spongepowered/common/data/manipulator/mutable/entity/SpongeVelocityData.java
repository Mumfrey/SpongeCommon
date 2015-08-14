package org.spongepowered.common.data.manipulator.mutable.entity;

import static org.spongepowered.api.data.DataQuery.of;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.entity.ImmutableVelocityData;
import org.spongepowered.api.data.manipulator.mutable.entity.VelocityData;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.common.data.manipulator.immutable.entity.ImmutableSpongeVelocityData;
import org.spongepowered.common.data.manipulator.mutable.common.AbstractSingleData;
import org.spongepowered.common.data.value.mutable.SpongeValue;

public class SpongeVelocityData extends AbstractSingleData<Vector3d, VelocityData, ImmutableVelocityData> implements VelocityData {

    public static final DataQuery VELOCITY_X = of("X");
    public static final DataQuery VELOCITY_Y = of("Y");
    public static final DataQuery VELOCITY_Z = of("Z");

    public SpongeVelocityData() {
        this(new Vector3d());
    }

    public SpongeVelocityData(Vector3d velocity) {
        super(VelocityData.class, velocity, Keys.VELOCITY);
    }

    @Override
    protected Value<?> getValueGetter() {
        return velocity();
    }

    @Override
    public VelocityData copy() {
        return new SpongeVelocityData();
    }

    @Override
    public ImmutableVelocityData asImmutable() {
        return new ImmutableSpongeVelocityData(getValue());
    }

    @Override
    public int compareTo(VelocityData o) {
        return o.velocity().get().compareTo(this.getValue());
    }

    @Override
    public DataContainer toContainer() {
        return new MemoryDataContainer()
            .createView(Keys.VELOCITY.getQuery())
            .set(VELOCITY_X, getValue().getX())
            .set(VELOCITY_Y, getValue().getY())
            .set(VELOCITY_Z, getValue().getZ())
            .getContainer();
    }

    @Override
    public Value<Vector3d> velocity() {
        return new SpongeValue<Vector3d>(Keys.VELOCITY, new Vector3d(), this.getValue());
    }
}