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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListaBinding.inflate(layoutInflater, container, false)
        loadTareas()
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadTareas() {
        GlobalScope.launch {
            val tareas = getDao().getTareas()
            val tareastexto = tareas.joinToString("\n") { it.nombre }
            showTareas(tareastexto)
        }
    }

    private suspend fun showTareas(tareas: String) {
        withContext(Dispatchers.Main){
            binding.tvMostrarLista.text = tareas
        }
    }

    private fun getDao(): TareaDao {
        return  TareaBaseDatos.getDatabase(this.requireContext()).getTaskDao()
    }

}