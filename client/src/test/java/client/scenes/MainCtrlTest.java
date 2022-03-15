/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package client.scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class MainCtrlTest {

    private MainCtrl sut;

    /**
     * Create new SUT before each Test
     */
    @BeforeEach
    public void setup() {
        sut = new MainCtrl();
    }

    /**
     * Test the getCurrentID method
     */
    @Test
    public void getCurrentIDTest() {
         sut.setCurrentGameID(42);
         assertEquals(42, sut.getCurrentID());
    }
}