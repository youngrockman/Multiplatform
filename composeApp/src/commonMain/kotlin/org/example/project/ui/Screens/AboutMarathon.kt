package org.example.project.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.ViewModel.IconPrinter
import org.example.project.ViewModel.TimerViewModel


class AboutMarathonScreen(private val timerViewModel: TimerViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = { AboutMarathonTopBar() },
            bottomBar = { AboutMarathonBottomBar(timerViewModel = timerViewModel) }
        ) { paddingValues ->

            Box(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 20.dp)
                ) {
                    val fontSize = (maxWidth.value / 25).sp
                    val fontSizeSecond = (maxWidth.value / 35).sp

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.weight(0.1f))

                        Text(
                            text = "Информация о Marathon Skills 2016",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = fontSize,
                        )

                        Spacer(modifier = Modifier.weight(0.1f))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(0.6f)
                                    .padding(end = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .aspectRatio(3f)
                                        .fillMaxWidth()
                                        .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(15.dp))
                                        .clip(RoundedCornerShape(15.dp))
                                        .background(Color.LightGray)
                                        .clickable { },
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconPrinter(
                                        resourceName = "map",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }


                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1.7f)
                                            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.LightGray)
                                            .clickable { },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        IconPrinter(
                                            resourceName = "map",
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1.7f)
                                            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.LightGray)
                                            .clickable { },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        IconPrinter(
                                            resourceName = "map",
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1.7f)
                                            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.LightGray)
                                            .clickable { },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        IconPrinter(
                                            resourceName = "map",
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1.7f)
                                            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.LightGray)
                                            .clickable { },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        IconPrinter(
                                            resourceName = "map",
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                }

                            }

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Информация о Marathon Skills 2016",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontSize = fontSizeSecond,
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(modifier = Modifier.weight(0.3f))

                                Text(
                                    text = "Информация о Marathon Skills 2016",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontSize = fontSizeSecond,
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(modifier = Modifier.weight(0.3f))

                                Text(
                                    text = "Информация о Marathon Skills 2016",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontSize = fontSizeSecond,
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(modifier = Modifier.weight(0.3f))
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun AboutMarathonTopBar() {
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
                            text = "Logout",
                            color = Color.Black,
                            fontSize = fontSizeButton
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun AboutMarathonBottomBar(timerViewModel: TimerViewModel) {
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
