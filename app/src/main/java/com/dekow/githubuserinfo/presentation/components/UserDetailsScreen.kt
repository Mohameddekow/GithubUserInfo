package com.dekow.githubuserinfo.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.dekow.githubuserinfo.R
import com.dekow.githubuserinfo.commons.utils.DammyData
import com.dekow.githubuserinfo.domain.model.User
import com.dekow.githubuserinfo.domain.model.UserDetails
import com.dekow.githubuserinfo.presentation.screen_routes.Screens
import com.dekow.githubuserinfo.presentation.user.UsersViewModel
import com.dekow.githubuserinfo.ui.theme.BackgroundBox
import com.dekow.githubuserinfo.ui.theme.DetailsItemBackgroundWhite
import com.dekow.githubuserinfo.ui.theme.ItemBackgroundWhite

@Composable
fun UserDetailScreen(
    navController: NavHostController,
    usersViewModel: UsersViewModel = hiltViewModel()
) {
    val followers = usersViewModel.followersState.value

    val following = usersViewModel.followingState.value

    val userState = usersViewModel.userState.value
    val user = userState.user

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        MyTopAppBar("Details", navController = navController)


        //error or loading box
        Box(
            modifier = Modifier.padding(4.dp),
            contentAlignment = Alignment.Center,
        ) {

            // error or loading state
            if(userState.error.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = userState.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }

            }
            if(userState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ){
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 100.dp)
                        .height(50.dp)
                        .width(50.dp)
                        .fillMaxWidth()
                    )
                }

            }
        }

        if (user != null) {
            UserDetailsItem(user = user)
            UserBiosRow(user = user)
        }

        MyRowItem(R.drawable.ic_round_persons,  user?.company.toString())
        MyRowItem(R.drawable.ic_round_location, user?.location.toString())
        MyRowItem(R.drawable.ic_round_link_24, user?.htmlUrl.toString())
        MyRowItem(
            R.drawable.ic_round_persons,
            "followers: ${user?.followers.toString()}      followings: ${user?.following.toString()}")

        user?.let { ReposRows(user = it) }


        MyLazyRowLists(
            following.userFollows,
            R.drawable.ic_round_persons,
            "Followings (${userState.user?.following})"
        )

        MyLazyRowLists(
            followers.userFollows,
            R.drawable.ic_round_persons,
            "Followers (${userState.user?.followers})"
        )

    }
}

@Composable
fun MyTopAppBar(
    text: String,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, top = 6.dp, end = 3.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(36.dp)
                .width(30.dp)
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(BackgroundBox)
                .clickable {
                    navController.navigate(Screens.UseListScreen.route) {
                        popUpTo(Screens.UseListScreen.route) {
                            inclusive = true
                        }
                    }
                },

        ) {

            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = text,
                modifier = Modifier
                    .padding(
                        start = 1.dp,
                        end = 1.dp
                    )

            )

        }

        Text(
            text = text,
            modifier = Modifier
                .weight(8f)
                .padding(2.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
        )
    }
}


@Composable
fun MyRowItem(
    icon: Int,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, top = 6.dp, end = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 1.dp, end = 3.dp)
        )

        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier
                .weight(6f)
                .padding(2.dp),
            textAlign = TextAlign.Start,
        )
    }
}


@Composable
fun ReposRows(
    user: UserDetails
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(DetailsItemBackgroundWhite),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Repositories",
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(1.dp)
            )

            Text(
                text = user.publicRepos.toString(),
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(1.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Organizations",
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(1.dp)
            )

            Text(
                text = user.publicGist.toString(),
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(1.dp)
            )

        }
    }

}



@Composable
fun UserBiosRow(
    user: UserDetails
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(
            text = user.bio!!,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,

            )
    }
}


@Composable
fun MyLazyRowLists(
    users: List<UserDetails>,
    icon: Int,
    text: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 1.dp, end = 3.dp)
            )

            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier.weight(6f)
            )

        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            contentPadding = PaddingValues(3.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(users) { user ->
                UserLazyRowItem(
                    userDetails = user,
                    DetailsItemBackgroundWhite
                )
            }
        }
    }
}


@Composable
fun UserDetailsItem(
    user: UserDetails
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 11.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(1.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .widthIn(90.dp)
                .height(110.dp)
                .padding(start = 15.dp, top = 5.dp, bottom = 5.dp, end = 1.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {

            Image(
                painter = rememberAsyncImagePainter(user.avatarUrl),
                contentDescription = "image",
                modifier = Modifier.size(100.dp)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 1.dp, end = 5.dp, top = 7.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = user.name ?: "name",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp),
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = user.login ?: "@login_name",
                textAlign = TextAlign.Start,
                fontSize = 19.sp,
                modifier = Modifier
                    .padding(start = 4.dp, top = 5.dp),
                fontWeight = FontWeight.Light,
            )
        }

    }

}


@Composable
fun UserLazyRowItem(
    userDetails: UserDetails,
    colors: Color,
    //onIconClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = colors),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .widthIn(90.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {


            //Image(painter = painterResource(user.placeHolderId!!), contentDescription = "placeholder")

            Image(
                painter = rememberAsyncImagePainter(userDetails.avatarUrl),
                contentDescription = "image",
                modifier = Modifier.size(100.dp)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = userDetails.login ?: "",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 4.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${userDetails.nodeId} - ${userDetails.id}",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp),
                fontWeight = FontWeight.Light,
            )
            Text(
                text = userDetails.htmlUrl ?: "no link provided",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp),
                fontWeight = FontWeight.Light,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MyPrev() {
    UserDetailScreen(navController = rememberNavController())
}