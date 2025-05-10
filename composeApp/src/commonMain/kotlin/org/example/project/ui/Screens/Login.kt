package org.example.project.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.Components.FieldWithLabel
import org.example.project.ViewModel.TimerViewModel
import org.example.project.ViewModel.UserViewModel

class LoginScreen(private val timerViewModel: TimerViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = { LoginTopBar() },
            bottomBar = { LoginBottomBar(timerViewModel = timerViewModel) }
        ) { paddingValues ->

            val viewModel = UserViewModel()
            val users = viewModel.getUsers()

            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 100.dp)
                ) {
                    val fontSize = (maxWidth.value / 25).sp
                    val fontSizeFirst = (maxWidth.value / 20).sp
                    val fontSizeSecond = (maxWidth.value / 35).sp
                    val labelWidth = 0.25f
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(Modifier.weight(0.25f))

                        Text(
                            text = "Форма авторизации",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            fontSize = fontSizeFirst,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.weight(0.25f))

                        Text(
                            text = "Пожалуйста, авторизуйтесь в системе, используя\n ваш адрес электронной почты и пароль",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            fontSize = fontSizeSecond,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Spacer(Modifier.weight(0.25f))

                        FieldWithLabel(
                            label = "Email:",
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "Enter your email address",
                            fontSize = fontSize,
                            labelWidth = labelWidth,
                        )

                        Spacer(Modifier.weight(0.25f))

                        FieldWithLabel(
                            label = "Password:",
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "Enter your password",
                            fontSize = fontSize,
                            labelWidth = labelWidth,
                        )

                        Spacer(Modifier.weight(0.25f))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    val user = users.find { it.email == email }
                                    if (user != null && user.password == password) {
                                        val userId = viewModel.getUserIdByEmail(user.email)
                                        navigator.push(InfoRunnerScreen(timerViewModel, userId))
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.LightGray,
                                    contentColor = Color.Black
                                ),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(
                                    "Login",
                                    fontSize = fontSize
                                )
                            }

                            Spacer(Modifier.weight(0.25f))

                            Button(
                                onClick = { navigator.push(MainScreen()) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.LightGray,
                                    contentColor = Color.Black
                                ),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(
                                    "Cancel",
                                    fontSize = fontSize
                                )
                            }
                        }

                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }

    @Composable
    private fun LoginTopBar() {
        val navigator = LocalNavigator.currentOrThrow

        Box {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
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
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.LightGray,
                            contentColor = Color.Black
                        ),
                        elevation = null,
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .wrapContentWidth()

                    ) {
                        Text(
                            text = "Назад",
                            color = Color.Black,
                            fontSize = fontSizeButton
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Text(
                        text = "MARATHON SKILLS 2016",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSizeText,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(64.dp))
                }
            }
        }
    }

    @Composable
    private fun LoginBottomBar(timerViewModel: TimerViewModel) {
        val remainingTime = timerViewModel.remainingTime.collectAsState().value
        Box {
            BoxWithConstraints(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                val fontSize = (maxWidth.value / 50).sp

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = remainingTime,
                        color = Color.White,
                        fontSize = fontSize
                    )
                }
            }
        }
    }
}