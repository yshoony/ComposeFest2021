package com.example.mybasicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybasicscodelab.ui.theme.MyBasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBasicsCodelabTheme {

//                var shouldShowOnboarding by remember{ mutableStateOf(true) }
                var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

                if (shouldShowOnboarding) {
                    OnboardingScreen { shouldShowOnboarding = false }
                } else {
                    MyApp()
                }
            }
        }
    }
}

@Composable
//private fun MyApp() {
//private fun MyApp(names: List<String> = listOf("World", "Compose")) {
private fun MyApp() {
    // A surface container using the 'background' color from the theme
//    Surface(color = MaterialTheme.colors.background) {
//        Greeting("Android")
//    }

//    Column(modifier = Modifier.padding(vertical = 4.dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }

    Greetings()

}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )
        }
    }
}

//TODO 애니메이션이 부자연스러움
//TODO card vs shape 의 차이인가?
//@Composable
//private fun Greeting(name: String) {
//    val expanded = remember { mutableStateOf(false) }
//
////    val extraPadding = if (expanded.value) 48.dp else 0.dp
////    val extraPadding by animateDpAsState(
////        if (expanded.value) 48.dp else 0.dp
////    )
//
//    val extraPadding by animateDpAsState(
//        if (expanded.value) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//        //패딩이 절대 음수가 되지 않아야하는데, 스프링 효과 주다보니 -까지 가는 듯하다.
//        //따라서 bottom = extraPadding.coerceAtLeast(0.dp) 이런식으로 -가 되지않도록 방어한다.
//    )
//
//    Surface(
//        color = MaterialTheme.colors.primary,
//        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//            ) {
//                Text(text = "Hello,")
//                Text(
//                    text = "$name", style = MaterialTheme.typography.h4.copy(
//                        fontWeight = FontWeight.ExtraBold
//                    )
//                )
//                if (expanded.value) {
//                    Text(
//                        text = ("Composem ipsum color sit lazy, " +
//                                "padding theme elit, sed do bouncy. ").repeat(4),
//                    )
//                }
//
//            }
//
////            OutlinedButton(
////                onClick = { expanded.value = !expanded.value }
////            ) {
//////                if (expanded.value) {
//////                    Text("Show more")
//////                } else {
//////                    Text("expended")
//////                }
////
////                Text(if (expanded.value) stringResource(R.string.show_less) else stringResource(R.string.show_more))
////            }
//
//            IconButton(onClick = { expanded.value = !expanded.value }) {
//                Icon(
//                    imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
//                    contentDescription = if (expanded.value) {
//                        stringResource(R.string.show_less)
//                    } else {
//                        stringResource(R.string.show_more)
//                    }
//
//                )
//            }
//        }
//    }
//}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun DefaultPreview() {
    MyBasicsCodelabTheme {
        MyApp()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MyBasicsCodelabTheme {
        OnboardingScreen { } //do nothing
    }
}
