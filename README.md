<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/devicon.min.css">
Проект Интернет магазина реализован 5 модулями:
1. admin_eshop. Админская часть где находятся слои DTO для обращения к модулю БД. Сервисы, Контроллеры для работы с User и Product. 
Так же реализованна часть отвечающая за Security. И страницы html.
2. backend_eshop. Часть которая работает с пользователем. Так же реализованы DTO слой для работы с модулем БД. Сервисы и Контроллеры. Конфигурации безопасности. 
Реализован RabbitMq как связующее звено для заказов. Так же реализованы классы и сервисы для работы с заказами.
3. eshop_dataBase. Модуль для работы с БД. Реализованы сущности и репозитории всех обьектов.
4. picture_service. Модуль для работы с картинками. Реализованы как DTO, сервис, контроллер.
5. shop-delivery-service. Модуль для работы с статусами заказов через RabbitMq.
6. shop-picture-service-api. Апи через которое идет работа с picture_service.
В контроллерах реализованы только основные методы add, delete, update.
На проекте использованы:
на Java 11
Сборка Maven
Архитектура REST

SprinFramework
SpringBoot
Lombok(для работы с БД)
Hebernate(для работы с БД)
Postgresql
Docker
SpringCloud(не закончено)
SpringSecurity
Liquibase(для работы с БД)
RabbitMQ
<!-- in your header -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/devicon.min.css">

<!-- in your body -->
<i class="devicon-devicon-plain"></i>
