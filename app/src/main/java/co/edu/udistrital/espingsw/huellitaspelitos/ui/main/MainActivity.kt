package co.edu.udistrital.espingsw.huellitaspelitos.ui.main

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.ActivityMainBinding
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoggedInUserView
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var mainActivityViewModel: MainViewModel

    private var loggedInUserView: LoggedInUserView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loggedInUserView = intent.getParcelableExtra(LoginActivity.LOGGED_IN_USER)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setSupportActionBar(binding.appBarMain.toolbar)
        //El botón para registrar nuevas mascotas al iniciar sesión
        binding.appBarMain.fab.setOnClickListener {
            goToCreatePet()
        }
        //Lo necesario para configurar el menú principal
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_pets, R.id.nav_services, R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Para que en el menú principal aparezcan los datos de quien inició sesión
        val headerView = navView.getHeaderView(0)
        val userHeaderTextView = headerView.findViewById<TextView>(R.id.username)
        val usermailHeaderTextView = headerView.findViewById<TextView>(R.id.usermail)
        userHeaderTextView.text = loggedInUserView?.login
        usermailHeaderTextView.text = loggedInUserView?.email

        loggedInUserView?.id?.let { mainActivityViewModel.setUserId(it) }
    }

    fun goToCreatePet(){
        binding.appBarMain.fab.hide()
        val bundle = Bundle()
        loggedInUserView?.id?.let { bundle.putInt(Constants.USER_ID, it) }
        navController.navigate(R.id.action_home_to_add_pet, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.appBarMain.fab.hide()
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigateUp(): Boolean {
        binding.appBarMain.fab.hide()
        return super.onNavigateUp()
    }

}