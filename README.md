# SF Movies App

> El README he creído conveniente escribirlo en Español para expresarme lo mejor posible. El resto de anotaciones en el proyecto y los String de la propia app están en Inglés.

---

**SF Movies** es una apliación con la que explorar todos los puntos de San Francisco donde se ha
filmado alguna película.

Puedes cargar todas las películas en rangos de tiempo 10 años o hacer una búsqueda de una película concreta.

## Arquitectura del proyecto

La arquitectura que he elegido es una implementación de la 'Clean Arquitecture' de [Uncle Bob](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html).
Y el proyecto está basado en un [ejemplo de Jorge Barroso](https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean).

Se divide en 3 capas (layers) donde por una parte está la vista que pinta la pantalla y utiliza un Presenter para comunicarse con la capa repositorio.

La capa repositorio se encarga de gestionar todos los datos que entran y salen de la app que son solicitados por los casos de uso de la misma.

Los casos de uso (Use cases) implementan la lógica de negocio del proyecto y están totalmente abstraídos del
framework de Android o de cualquier otra librería usada.

La idea de usar esta arquitectura es conseguir una aplicación fácil de testar y mantener a lo largo del tiempo.

## Librerías usadas

Empezando por la parte de la View, he utilizado **ButterKnife** para inyectar las vistas usadas en cada pantalla. Y **Materialrangebar** para pintar un seekbar con 2 thumbs.

Importé **Dexter** como helper para los permisos que requiera la app, como usar el micrófono en la búsqueda.

Para las peticiones externas a la app (HTTP) empecé importando **Retrofit** pero esta librería mira más al consumo de APIs REST, así que finalmente opté por usar OkHTTP y parsear el Json obtenido con la librería de Google **GSon**.

En la parte del almacenamiento local he decidido abstraerme de trabajar directamente con SQLite y así ahorrar tiempo usando un ORM, dado que no es una tabla de datos muy compleja ni hago uso muy complicado de los datos almacenados, he decidido usar un ORM liviano como es **SugarORM**.

El resto de librerías importadas no creo que haga falta comentarlas.

## Si hubiera tenido mas tiempo...

Desde el primer momento he pensado en diferentes sistemas para filtrar resultados y no cargar mucho el mapa con un montón de markers.

Implementar la ubicación del usuario para mostrar notificaciones al pasar cerca de una zona en la que se haya filmado alguna película.

Y finalmente implementar una pantalla dónde mostrar algo de información extra sobre la película seleccionada.
