# коллок

## Запуск

Для начала требуется поднять все контейнеры от которых зависит данное приложение.
Можно сделать это с помощью файла [```docker-compose.yml```](/docker/docker-compose.yml) и cli
инструмента docker-compose, прописав команду:
```docker-compose -f <path to docker.compose.yml> up -d```.
В случае, если вы находитесь в директории проекта, можно прописать:
```docker-compose -f docker/docker-compose.yml up -d```

Данный docker-compose поднимает postgres и vault.
Так как vault при каждом перезапуске закрывается(sealed), требуется взять ключ под
названием ```keys_base64```
из [данного кофнига](docker/vault/config/vault-cluster-vault-2023-06-10T06_55_05.570Z.json), перейти
по http://localhost:8200/ui и вставить ключ в поле для ввода.
Теперь можно запускать программу.