package com.example.addressbook250727

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.addressbook250727.ui.theme.AddressBook250727Theme

import com.example.addressbook250727.data.AddressBook
import com.example.addressbook250727.ui.objects.AddressBookForm
import com.example.addressbook250727.ui.objects.AddressBookViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: AddressBookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddressBook250727Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddressBookForm(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )


                    /*
                                        Greeting(
                                            name = "Android",
                                            modifier = Modifier.padding(innerPadding)
                                        )

                                         */

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AddressBook250727Theme {
        Greeting("Android")
    }
}