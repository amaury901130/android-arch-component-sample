# Android MVVM y Arch. Components

## Arch. MVVM con patron Observer

### Model-View-ViewModel aplicado a Android, se compone de tres elementos principales:
- View : Se encarga de mostra la UI al suario final, está subscrito a observers del viewmodel, está compuesto por una
UI-Controller y la vista, inicialmente esta pensado que la UI-Controller sea el Activity|Fragment , pero no necesariamente 
es así, hay muchas maneras de implementarlo, una recomendación es utilizar una interfaz por view para mantener el 
codigo organizado dependiendo de la complejida que este tenga.

- View model: Se encarga interactuar con la modelo y notificarle los cambios a la UI-Controller, esta capa está
subscrita al lifecycle de la Activity|Fragment|Servicio que la utiliza para mantener siempre la consistencia de los datos.

- Model: Es la capa abstracta que le provee los datos al viewmodel sin importar de donde vengan ya sea de BD|API|Cache|Almacenamiento local.

---

## Arch Components:
Librerias que forman parte del JetPack de android de Google para crear apps más estables y faciles de mantener.

- [Architecture Component](https://developer.android.com/topic/libraries/architecture)
- [Ejemplo Codelab](https://codelabs.developers.google.com/codelabs/android-lifecycles/#0)
- [Ejemplos Google](https://developer.android.com/topic/libraries/architecture/additional-resources.html#ejemplos)

En este ejemplo se utilizó:

- ViewModel: explicado anteriormente

    [Ver más](https://medium.com/androiddevelopers/viewmodels-a-simple-example-ed5ac416317e)

- LiveData: Es una clase que hereda de observers y se subscribe al lifecycle de la app para asegurarse que solo funcione 
durante el lifecycle de la Activity,Fragment o Servicio que lo está ejecutando.
    
    [Ver más](https://proandroiddev.com/when-and-why-to-use-android-livedata-93d7dd949138)

- Databinding: Se utiliza para facilitar el uso de la vista, puedes pasarle variables desde cualquier interfaz
esto facilita la reutilización de código (DRY) ejemplo:
    Digamos que tienes una vista que muestra la informacion de un usuario, nombre, apellidos, foto etc....
    cada vez que la utilices tendrías que hacer algo como:
    
            name.text = user.name
            lastName.text = user.lastname
            foto.load = user.avatar
        
   en cambio si le pasas el objeto usuario a la vista ya ella se encaga de eso sin importar de donde venga.
       
           <layout xmlns:android="http://schemas.android.com/apk/res/android" xmlns:app="http://schemas.android.com/apk/res-auto">
               <data>
                   <variable name="user" type="com.ejemplo.model.User"/>
               </data>
                   <LinearLayout android:orientation="vertical"
                                 android:layout_width="match_parent"
                                 android:layout_height="wrap_content">
                       <TextView android:layout_width="match_parent"
                                 style="@style/TextAppearance.AppCompat.Widget.ActionBar.Title"
                                 android:layout_height="wrap_content"
                                 android:text="@{user.name}"/>
                       <TextView android:layout_width="match_parent"
                                 android:layout_marginTop="2dp"
                                 android:layout_marginStart="5dp"
                                 style="@style/TextAppearance.AppCompat.Widget.ActionBar.Subtitle"
                                 android:layout_height="wrap_content"
                                 android:text="@{user.lastName}"/>
                       <ImageView
                                 android:layout_width="match_parent"
                                 style="@style/TextAppearance.AppCompat.Widget.ActionBar.Title"
                                 android:layout_height="wrap_content"
                                 app:imageUrl="@{user.avatar}"
                                 />
                   </LinearLayout>
           </layout>
       
   Note: app:imageUrl="@{user.avatar}" es un BindingAdapter:
       
           @BindingAdapter(value = ["app:imageUrl", "app:placeholderResource", "app:errorResource"], requireAll = false)
           fun setLoadImage(
               imageView: ImageView,
               imageUrl: String,
               placeholderResource: Int? = null,
               errorResource: Int? = null
           ) {
               loadImage(Picasso.get().load(imageUrl), imageView, placeholderResource, errorResource)
           }
       
           private fun loadImage(
             piccasoCreator: RequestCreator?,
             imageView: ImageView,
             placeholderResource: Int?,
             errorResource: Int?
           ) {
             if (placeholderResource != null) piccasoCreator!!.placeholder(placeholderResource)
             if (errorResource != null) piccasoCreator!!.error(errorResource)
             piccasoCreator!!.into(imageView)
           }

   Las posibilidades de Databinding son muy variadas, puedes pasarle todo tipo de objectos e incluso usar livedata 
   en la vista para mantenerla actualizada.

   [Observable Fields to LiveData](https://medium.com/androiddevelopers/android-data-binding-library-from-observable-fields-to-livedata-in-two-steps-690a384218f2)
   
   [Databinding in Kotlin](https://proandroiddev.com/advanced-data-binding-binding-to-livedata-one-and-two-way-binding-dae1cd68530f)
   
- Paging: Es un adapter que hereda de RecyclerView.Adapter y nos permite cargar datos paginados de está manera la vista tiene un 
comportamiento más fluido.
    
   [Ver más](https://proandroiddev.com/8-steps-to-implement-paging-library-in-android-d02500f7fffe)
    
- Room: base de datos SQLite, generalmente utilizada para crear una caché de tus datos y cargarlos de manera persistente, 
muy útil para el trabajo con paging.

    [Ver más](https://medium.com/@tonia.tkachuk/android-app-example-using-room-database-63f7091e69af)

    [Ejemplo](https://github.com/googleejemplos/android-sunflower)

## Otros elementos de Arch Componente:
- WorkManager: manejo de tareas en segundo plano.
     
    [Ver más](https://developer.android.com/topic/libraries/architecture/workmanager)
---
##Tambien deberías ver
[Android JetPack by Google](https://developer.android.com/jetpack)
