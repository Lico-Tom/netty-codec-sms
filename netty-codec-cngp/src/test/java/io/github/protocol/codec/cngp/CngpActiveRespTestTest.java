/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.github.protocol.codec.cngp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CngpActiveRespTestTest {

    @Test
    public void case1() {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        Mockito.when(ctx.alloc()).thenReturn(ByteBufAllocator.DEFAULT);
        CngpHeader header = new CngpHeader(CngpConst.ACTIVE_TEST_RESP_ID, 1, 3);
        ByteBuf buf = CngpEncoder.INSTANCE.doEncode(ctx, new CngpActiveRespTest(header));
        CngpActiveRespTest message = (CngpActiveRespTest) new CngpDecoder().decode(buf);
        Assertions.assertEquals(3, message.header().sequenceId());
        Assertions.assertEquals(1, message.header().commandStatus());
        Assertions.assertEquals(0, buf.readableBytes());
    }
}
