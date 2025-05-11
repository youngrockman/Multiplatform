package org.example.project.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.*
import org.example.project.Components.FieldWithLabel
import org.example.project.ViewModel.TimerViewModel
import org.example.project.ViewModel.UserViewModel

class LoginScreen(private val timerViewModel: TimerViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = remember { UserViewModel() }
        val users = remember { viewModel.getUsers() }

        Scaffold(
            topBar = { TopBar(navigator) },
            bottomBar = { BottomBar(timerViewModel) }
        ) { paddingValues ->
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 32.dp)
            ) {
                val maxW = maxWidth
                val fontScale = maxW.value / 100
                val fontSizeLarge = fontScale.sp * 2
                val fontSizeMedium = fontScale.sp * 1.5f
                val fontSizeButton = fontScale.sp

                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Форма авторизации",
                        fontSize = fontSizeLarge,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Пожалуйста, авторизуйтесь в системе, используя\nваш адрес электронной почты и пароль",
                        fontSize = fontSizeMedium,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )

                    FieldWithLabel(
                        label = "Email:",
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Enter your email address",
                        fontSize = fontSizeMedium,
                        labelWidth = 0.25f
                    )

                    FieldWithLabel(
                        label = "Password:",
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Enter your password",
                        fontSize = fontSizeMedium,
                        labelWidth = 0.25f
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                val user = users.find { it.email == email }
                                if (user != null && user.password == password) {
                                    val userId = viewModel.getUserIdByEmail(user.email)
                                    navigator.push(InfoRunnerScreen(timerViewModel, userId))
                                }
                            },
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Login", fontSize = fontSizeButton)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = { navigator.push(MainScreen()) },
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Cancel", fontSize = fontSizeButton)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun TopBar(navigator: Navigator) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val fontSizeButton = (maxWidth.value / 50).sp
            val fontSizeText = (maxWidth.value / 25).sp

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navigator.push(MainScreen()) },
                    shape = RoundedCornerShape(15.dp),
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Назад", fontSize = fontSizeButton)
                }

                Text(
                    text = "MARATHON SKILLS 2016",
                    color = Color.White,
                    fontSize = fontSizeText,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(64.dp))
            }
        }
    }

    @Composable
    private fun BottomBar(timerViewModel: TimerViewModel) {
        val remainingTime = timerViewModel.remainingTime.collectAsState().value
        BoxWithConstraints {
            val fontSize = (maxWidth.value / 50).sp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = remainingTime,
                    fontSize = fontSize,
                    color = Color.White
                )
            }
        }
    }
}
