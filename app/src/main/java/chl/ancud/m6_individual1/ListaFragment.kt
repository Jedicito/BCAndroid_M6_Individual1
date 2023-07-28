package chl.ancud.m6_individual1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chl.ancud.m6_individual1.databinding.FragmentListaBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaFragment : Fragment() {

    lateinit var binding: FragmentListaBinding
    lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListaBinding.inflate(layoutInflater, container, false)
        initRepositorio()
        loadTareas()
        return binding.root
    }

    private fun initRepositorio() {
        repositorio = Repositorio(TareaBaseDatos.getDatabase(requireContext()).getTaskDao())
    }

    private fun loadTareas() {
        repositorio.listTareas().observe(requireActivity()){
            val tareasTexto = it.joinToString("\n") { it.nombre }
            showTareas(tareasTexto)
        }
    }

    private fun showTareas(tareas: String) {
        binding.tvMostrarLista.text = tareas
    }

}