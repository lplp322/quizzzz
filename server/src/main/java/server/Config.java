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
package server;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.database.ActivityRepository;

@Configuration
public class Config {
    @Autowired
    private ActivityRepository dtBase;

    /**
     * returns random
     * @return a Random object
     */
    @Bean
    public Random getRandom() {
        return new Random();
    }

    /**
     * Adds 4 activities in case the database is empty
     */
    @Bean
    public void addTempActivities() {
        if(dtBase.getAllActivities().size() < 4) {
            dtBase.save(new Activity("A", 101));
            dtBase.save(new Activity("B", 102));
            dtBase.save(new Activity("C", 103));
            dtBase.save(new Activity("D", 104));
        }
        //System.out.println(dtBase.getAllActivities().size());
    }
}