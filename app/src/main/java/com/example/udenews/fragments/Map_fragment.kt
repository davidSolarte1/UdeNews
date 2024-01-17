package com.example.udenews.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.udenews.R
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

class Map_fragment : Fragment() {

    private lateinit var mapView: MapView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        // Inicializar la configuración de osmdroid

        Configuration.getInstance().load(
            requireContext(),
            requireActivity().getSharedPreferences("osmdroid", 0)
        )

        mapView = view.findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK) // Establecer el origen de los azulejos (tiles)

        // Configurar la posición inicial del mapa y el nivel de zoom

        mapView.controller.setZoom(20.0)
        val latitud = 1.2323 // Ejemplo de latitud (punto flotante)
        val longitud = -77.293 // Ejemplo de longitud (punto flotante)
        val geoPoint = GeoPoint(latitud, longitud) // Creación de un objeto GeoPoint

        mapView.controller.setCenter(geoPoint)


        val Entrada = mutableListOf(
            GeoPoint(1.231229, -77.293307),
        )


        val routePlazaFuchi = mutableListOf(
            GeoPoint(1.231229, -77.293307),
            GeoPoint(1.232015, -77.292942),
        )


        val routeLaboratorios = mutableListOf(
            GeoPoint(1.231229, -77.293307),
            GeoPoint(1.232015, -77.292942),
            GeoPoint(1.232086, -77.292942),
            GeoPoint(1.232339, -77.293538),
            GeoPoint(1.232430, -77.293705),
            GeoPoint(1.232612, -77.293640),
            GeoPoint(1.232537, -77.293512)
        )



        val routeBloqueTecnologico = mutableListOf(
            GeoPoint(1.231229, -77.293307),
            GeoPoint(1.232015, -77.292942),
            GeoPoint(1.232086, -77.292942),
            GeoPoint(1.232339, -77.293538),
            GeoPoint(1.232430, -77.293705),
            GeoPoint(1.232612, -77.293640),
            GeoPoint(1.233002, -77.293503),
            GeoPoint(1.232965, -77.293359),
        )

        val routeFacultadIng = mutableListOf(
            GeoPoint(1.231229, -77.293307),
            GeoPoint(1.232015, -77.292942),
            GeoPoint(1.232086, -77.292942),
            GeoPoint(1.232339, -77.293538),
            GeoPoint(1.232430, -77.293705),
            GeoPoint(1.232612, -77.293640),
            GeoPoint(1.232719, -77.293919),
        )

        val routeBienestar = mutableListOf(
            GeoPoint(1.231229, -77.293307),
            GeoPoint(1.232015, -77.292942),
            GeoPoint(1.232078, -77.292929),
            GeoPoint(1.232115, -77.292904),
            GeoPoint(1.232125, -77.292895),
            GeoPoint(1.232543, -77.292683),
            GeoPoint(1.232570, -77.292742),
            GeoPoint(1.232858, -77.292590),
            GeoPoint(1.233129, -77.293158),
            GeoPoint(1.233312, -77.293077)
        )


        val routeBiblioteca = mutableListOf(
            GeoPoint(1.231229, -77.293307),
            GeoPoint(1.231724, -77.293080),
            GeoPoint(1.231539, -77.292644),
        )

        val routeBloqueAdmin = mutableListOf(
            GeoPoint(1.231229, -77.293307),
            GeoPoint(1.231405, -77.293214),
            GeoPoint(1.231129, -77.292669),
            GeoPoint(1.231234, -77.292610),
        )


        addRoute(Entrada,"Entrada Universidad de Nariño")
        addRoute(routePlazaFuchi,"Plaza Fuchi")

        addRoute(routeLaboratorios,"Laboratorios")
        addRoute(routeBloqueTecnologico,"Bloque Tecnologico")
        addRoute(routeFacultadIng,"Facultad de Ingenieria")
        addRoute(routeBienestar,"Bienestar Universitario")
        addRoute(routeBiblioteca,"Biblioteca")
        addRoute(routeBloqueAdmin,"Bloque Administrativo")






        return view
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }


    private fun addRoute(points: List<GeoPoint>, routeName: String) {
        val polyline = Polyline()
        polyline.setPoints(points)
        polyline.outlinePaint.color = resources.getColor(R.color.routeColor)
        polyline.outlinePaint.strokeWidth = 5f
        mapView.overlayManager.add(polyline)

        // Agrega un marcador con el nombre al final de la ruta
        val endPoint = points.last()
        val marker = Marker(mapView)
        marker.position = endPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = routeName

        mapView.overlays.add(marker)

        mapView.invalidate()
    }



}
