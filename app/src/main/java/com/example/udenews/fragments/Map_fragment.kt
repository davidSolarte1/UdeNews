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
        Configuration.getInstance().load(requireContext(),
            requireActivity().getSharedPreferences("osmdroid", 0))

        mapView = view.findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK) // Establecer el origen de los azulejos (tiles)

        // Configurar la posición inicial del mapa y el nivel de zoom
        mapView.controller.setZoom(20.0)
        val latitud = 1.2323 // Ejemplo de latitud (punto flotante)
        val longitud = -77.293 // Ejemplo de longitud (punto flotante)
        val geoPoint = GeoPoint(latitud, longitud) // Creación de un objeto GeoPoint

        mapView.controller.setCenter(geoPoint)

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
}
