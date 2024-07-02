#!/bin/bash
docker run -p 3307:3306 --name snake_game --env-file .env -d snake_game_app
