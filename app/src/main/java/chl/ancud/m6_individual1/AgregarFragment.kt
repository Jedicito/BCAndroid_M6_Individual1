package chl.ancud.m6_individual1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import chl.ancud.m6_individual1.databinding.FragmentAgregarBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarFragment : Fragment() {

    lateinit var binding: FragmentAgregarBinding
    lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgregarBinding.inflate(layoutInflater, container, false)

        initRepositorio()
        initListener()

        return binding.root
    }

    private fun initRepositorio() {
        repositorio = Repositorio(TareaBaseDatos.getDatabase(requireContext()).getTaskDao())
    }

    private fun initListener() {
        binding.btIngresar.setOnClickListener {
            val texto = binding.etIngreso.text.toString()
            saveTarea(texto)
            Toast.makeText(context, "$texto guardado", Toast.LENGTH_SHORT).show()
            binding.etIngreso.text.clear()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveTarea(texto: String) {
        val dao = TareaBaseDatos.getDatabase(requireContext()).getTaskDao()
        val tarea = Tarea(texto)
        GlobalScope.launch {  repositorio.insertTarea(tarea) }
    }



}