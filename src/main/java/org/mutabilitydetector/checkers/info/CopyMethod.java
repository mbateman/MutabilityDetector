/*
 *    Copyright (c) 2008-2011 Graham Allan
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.mutabilitydetector.checkers.info;

import javax.annotation.concurrent.Immutable;

import org.mutabilitydetector.locations.Dotted;
import org.objectweb.asm.tree.MethodInsnNode;

import com.google.common.base.Objects;

@Immutable
public class CopyMethod {
    private final Dotted owner;
    private final String name;
    private final String desc;
    
    public CopyMethod(Dotted owner, String name, String desc) {
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }
    
    public static CopyMethod from(MethodInsnNode methodNode) {
        return new CopyMethod(Dotted.dotted(methodNode.owner), methodNode.name, methodNode.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(desc, name, owner);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CopyMethod other = (CopyMethod) obj;
        return desc.equals(other.desc) && name.equals(other.name) && owner.equals(other.owner);
    }
    
    @Override
    public String toString() {
    	return Objects.toStringHelper(this)
    			.add("owner", owner)
    			.add("name", name)
    			.add("desc", desc)
    			.toString();
    }
    
}