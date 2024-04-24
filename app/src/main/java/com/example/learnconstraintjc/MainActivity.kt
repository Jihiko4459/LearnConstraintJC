package com.example.learnconstraintjc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.learnconstraintjc.ui.theme.LearnConstraintJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnConstraintJCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LearnConstraintLayout()
                }
            }
        }
    }
}

@Composable
fun LearnConstraintLayout(){
    ConstraintLayout {
        val context= LocalContext.current.applicationContext
        val (redButton, greenButton, blueButton, blackButton)=createRefs()
        Button(onClick = {Toast.makeText(context, "Red", Toast.LENGTH_SHORT).show()},
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier.constrainAs(redButton){
                top.linkTo(parent.top)//привязка сверху к родителю
                width= Dimension.matchParent
                height= Dimension.value(100.dp)
            }) {
            Text(text = "Red")
        }
        Button(onClick = {Toast.makeText(context, "Green", Toast.LENGTH_SHORT).show()},
            colors = ButtonDefaults.buttonColors(Color.Green),
            modifier = Modifier.constrainAs(greenButton){
                top.linkTo(redButton.bottom)//привязка снизу к redButton

            }) {
            Text(text = "Green")
        }
        Button(onClick = {Toast.makeText(context, "Blue", Toast.LENGTH_SHORT).show()},
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier.constrainAs(blueButton){
                top.linkTo(redButton.bottom)
            }) {
            Text(text = "Blue")
        }
        val guideLine=createGuidelineFromBottom(0.01f)
        createHorizontalChain(greenButton, blueButton, chainStyle = ChainStyle.Packed)
        Button(onClick = {Toast.makeText(context, "Black", Toast.LENGTH_SHORT).show()},
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.constrainAs(blackButton){
//                top.linkTo(blueButton.bottom)
                bottom.linkTo(guideLine)
            }) {
            Text(text = "Black")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnConstraintJCTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LearnConstraintLayout()
        }
    }
}