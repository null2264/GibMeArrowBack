/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.null2264.gibmearrowback;

import net.minecraft.client.model.ModelPart;

import java.util.List;

public interface GMABInjectedEntityModel {
    List<ModelPart> getParts();
}