#!/bin/bash
docker run -p 3307:3306 --name snake_game --env-file .env -d lcaohoanq/snake_game_app:2.0