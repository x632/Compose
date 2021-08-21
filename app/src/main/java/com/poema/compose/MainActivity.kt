package com.poema.compose

import android.accounts.AuthenticatorDescription
import android.os.Bundle
import android.provider.Telephony
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import org.intellij.lang.annotations.JdkConstants


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //MessageCard("Android")
            /*   val painter = painterResource(id = R.drawable.happy)
            val description = "Happy dude"
            val title = "Happy dude, heigh up.."
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF404040))){
                Box(modifier = Modifier.fillMaxWidth(0.5f)){
                    ImageCard(painter = painter,contentDescription = description, title = title)
                }
            }*/
            //ColorBox(Modifier.fillMaxSize())
            /*   Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                ){
                CircularProgressbar(percentage = 1f, number = 100)
            }*/
            val messages = listOf(
                Message("Title1", "Andreas", 1),
                Message("Title2", "Jenny", 2),
                Message("Title3", "Isak", 3),
                Message("Title4", "Julia", 4),
                Message("Title5", "Andreas", 5),
                Message("Title6", "Andreas", 6),
                Message("Title7", "Andreas", 7),
                Message("Title8", "Andreas", 8),
                Message("Title9", "Andreas", 9)
            )

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(0.30f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 5.dp,
                backgroundColor = Color(0xFF3D4936)

            ) {
                TestingRowsAndColumns()
            }
            //Conversation(messages)

        }
    }

    @Composable
    fun MessageCard(name: String) {

        Column(
            modifier = Modifier
                .fillMaxHeight(0.50f)
                .fillMaxWidth(0.5f)
                .padding(16.dp)
                .border(3.dp, Color.Black)
                .background(Color.Green)
                .padding(16.dp),

            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text("Hello $name", modifier = Modifier.clickable {
            })
            Text("This is Compose!")
            Text("Let's see how to do things..")
        }
    }

    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            elevation = 10.dp
        ) {
            Box(modifier = Modifier.height(130.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ), startY = 295f
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(title, style = TextStyle(color = Color(0xFFDEE5EB), fontSize = 14.sp))
                }


            }

        }
    }

    @Composable
    fun ColorBox(modifier: Modifier = Modifier) {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }
        Box(modifier = modifier
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            })
    }

    @Composable
    fun CircularProgressbar(
        percentage: Float,
        number: Int,
        fontSize: TextUnit = 28.sp,
        radius: Dp = 50.dp,
        color: Color = Color.Green,
        strokeWidth: Dp = 12.dp,
        animDuration: Int = 3000,
        animDelay: Int = 0
    ) {
        var animationPlayed by remember {
            mutableStateOf(false)
        }
        val curPercentage = animateFloatAsState(
            targetValue = if (animationPlayed) percentage else 0f,
            animationSpec = tween(
                durationMillis = animDuration,
                delayMillis = animDelay
            )
        )
        LaunchedEffect(key1 = true) {
            animationPlayed = true
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(radius * 2f)
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawArc(
                    color = color,
                    -90f,
                    360 * curPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Text(
                text = (curPercentage.value * number).toInt().toString(),
                color = Color.Black,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        val painter = painterResource(id = R.drawable.happy)
        LazyColumn {
            items(messages) { mess ->
                MessageCard(mess, painter)
            }
        }
    }

    @Composable
    fun MessageCard(message: Message, painter: Painter) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                //.background(Color.Green)
                .padding(5.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp,
        )
        {
                Column(
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(10.dp, 8.dp)
                        .fillMaxSize()
                )
                {
                    Row(
                        modifier = Modifier
                            .padding(10.dp, 8.dp)
                            .fillMaxSize(),

                        ) {
                        Text(message.title, color = Color.White)
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp, 4.dp)
                            .fillMaxSize()
                    ) {
                        Text(message.something, color = Color.White)
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp, 5.dp)
                            .fillMaxSize()
                    ) {
                        Text(message.number.toString(), color = Color.White)
                    }
                }

                    Box(
                        modifier = Modifier
                            .padding(0.dp, 15.dp)
                            .requiredWidth(130.dp)
                            .height(80.dp)
                        ,
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }

        }

    }

    @Composable
    fun TestingRowsAndColumns(){

             Row(horizontalArrangement = Arrangement.SpaceEvenly,
                 verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier

                  .fillMaxSize()



              ){
                    Column(modifier = Modifier
                        .fillMaxHeight(0.7f)
                     .background(Color.Black),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text("Testing this ", color = Color.White)
                        Text("Testing this ", color = Color.Blue)
                        Text("Testing this ", color = Color.Green)
                    }
                    Column(modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .background(Color.Black),
                            verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text("Testing this ", color = Color.White)
                        Text("Testing this ", color = Color.Blue)
                        Text("Testing this ", color = Color.Green)
                }
                     Column(modifier = Modifier
                         .fillMaxHeight(0.7f)
                         .background(Color.Black),
                         verticalArrangement = Arrangement.SpaceEvenly
                     ){
                         Text("Testing this ", color = Color.White)
                        Text("Testing this ", color = Color.Blue)
                        Text("Testing this ", color = Color.Green)
                    }
                }



    }
}

data class Message(val title: String, val something : String, val number: Int)