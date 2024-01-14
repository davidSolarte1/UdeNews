package com.example.udenews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


class Map : AppCompatActivity() {

    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Inicializar la configuración de osmdroid (se recomienda hacerlo antes de inflar el diseño)
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", 0))

        setContentView(R.layout.activity_map)

        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK) // Establecer el origen de los azulejos (tiles)

        // Configurar la posición inicial del mapa y el nivel de zoom

        mapView.controller.setZoom(20.0)
        //mapView.controller.setCenter(51.5074, -0.1278) // Coordenadas de Londres (ejemplo)
        val latitud = 1.2323 // Ejemplo de latitud (punto flotante)
        val longitud = -77.293 // Ejemplo de longitud (punto flotante)
        val geoPoint = GeoPoint(latitud, longitud) // Creación de un objeto GeoPoint

        mapView.controller.setCenter(geoPoint)



    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
