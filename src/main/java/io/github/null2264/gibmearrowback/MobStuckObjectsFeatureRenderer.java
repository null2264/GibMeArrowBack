/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.null2264.gibmearrowback;

import io.github.null2264.gibmearrowback.mixin.ModelPartAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Random;

/**
 * Directly copied from {@link net.minecraft.client.render.entity.feature.StuckObjectsFeatureRenderer}
 */
@Environment(EnvType.CLIENT)
public abstract class MobStuckObjectsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    public MobStuckObjectsFeatureRenderer(LivingEntityRenderer<T, M> entityRenderer) {
        super(entityRenderer);
    }

    protected abstract int getObjectCount(T entity);

    protected abstract void renderObject(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float directionX, float directionY, float directionZ, float tickDelta);

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        int m = this.getObjectCount(livingEntity);
        Random random = new Random(livingEntity.getId());
        if (m > 0) {
            for(int n = 0; n < m; ++n) {
                matrixStack.push();

                /* start of GMAB */
                EntityModel<T> model = this.getContextModel();
                List<ModelPart> parts = ((GMABInjectedEntityModel) model).getParts();
                ModelPart modelPart = parts.get(random.nextInt(parts.size()));
                List<ModelPart.Cuboid> cuboids = ((ModelPartAccessor)(Object)modelPart).getCuboids();
                if (cuboids.isEmpty()) {
                    matrixStack.pop();
                    return;
                }
                ModelPart.Cuboid cuboid = cuboids.get(random.nextInt(cuboids.size()));
                /* end of GMAB */

                modelPart.rotate(matrixStack);
                float o = random.nextFloat();
                float p = random.nextFloat();
                float q = random.nextFloat();
                float r = MathHelper.lerp(o, cuboid.minX, cuboid.maxX) / 16.0F;
                float s = MathHelper.lerp(p, cuboid.minY, cuboid.maxY) / 16.0F;
                float t = MathHelper.lerp(q, cuboid.minZ, cuboid.maxZ) / 16.0F;
                matrixStack.translate(r, s, t);
                o = -1.0F * (o * 2.0F - 1.0F);
                p = -1.0F * (p * 2.0F - 1.0F);
                q = -1.0F * (q * 2.0F - 1.0F);
                this.renderObject(matrixStack, vertexConsumerProvider, i, livingEntity, o, p, q, h);
                matrixStack.pop();
            }

        }
    }
}