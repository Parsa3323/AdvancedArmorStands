/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsa3323.aas.api.data;

import org.bukkit.util.EulerAngle;

public class ArmorStandPoseData {
    private EulerAngle rightArm, leftArm, rightLeg, leftLeg, head;

    public ArmorStandPoseData(EulerAngle rightArm, EulerAngle leftArm, EulerAngle rightLeg, EulerAngle leftLeg, EulerAngle head) {
        this.rightArm = rightArm;
        this.leftArm = leftArm;
        this.rightLeg = rightLeg;
        this.leftLeg = leftLeg;
        this.head = head;
    }

    public EulerAngle getRightArm() { return rightArm; }
    public EulerAngle getLeftArm() { return leftArm; }
    public EulerAngle getRightLeg() { return rightLeg; }
    public EulerAngle getLeftLeg() { return leftLeg; }
    public EulerAngle getHead() { return head; }
}