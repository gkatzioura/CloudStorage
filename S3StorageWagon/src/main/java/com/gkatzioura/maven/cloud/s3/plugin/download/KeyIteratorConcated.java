/*
 * Copyright 2018 Emmanouil Gkatziouras
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gkatzioura.maven.cloud.s3.plugin.download;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class KeyIteratorConcated implements Iterator<String> {

    private final List<Iterator<String>> iterators;

    public KeyIteratorConcated(List<Iterator<String>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        if(iterators.size()>0) {
            if(!iterators.get(0).hasNext()) {
                iterators.remove(0);
                return hasNext();
            }

            return true;
        }

        return false;
    }

    @Override
    public String next() {
        if(!hasNext()) {
            return null;
        }

        Iterator<String> stringIterator = iterators.get(0);

        return stringIterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
        throw new UnsupportedOperationException();
    }
}
