import { Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { ContactComponent } from './modules/contact/contact.component';
import { AboutMeComponent } from './modules/aboutMe/aboutMe.component';
import { RelatedLinksComponent } from './modules/relatedLinks/relatedLinks.compoment';
import { PriceListComponent } from './modules/priceList/priceList.component';
import { GamesComponent } from './modules/games/games.component';
import { AuthComponent } from './modules/auth/auth.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { CourseAddFormComponent } from './modules/course/courseAddForm.component';
import { AuthGuard } from './modules/auth/auth.guard';
import { MyCoursesComponent } from './modules/course/myCourses/myCourses.component';
import { ShopComponent } from './modules/shop/shop.component';
import { CartComponent } from './modules/shop/cart/cart.component';
import { OrdersComponent } from './modules/order/orders.component';
import { CourseComponent } from './modules/course/course.component';
import { AllCoursesComponent } from './modules/course/allCourses.component';
import { CartSummaryComponent } from './modules/shop/cart/cartSummary.component';
import { ShopItemFormComponent } from './modules/shop/shopItemForm.component';
import { DiscountCodesComponent } from './modules/shop/discountCode/discountCodes.component';
import { StudentsComponent } from './modules/student/students.component';
import { TasksComponent } from './modules/task/tasks.component';
import { ShelfComponent } from './modules/shelf/shelf.component';
import { FileManagerComponent } from './modules/fileManager/fileManager.component';
import { AccountSettingsComponent } from './modules/user/accountSettings.component';
import { AllOrdersComponent } from './modules/order/allOrders.component';
import { ShopItemComponent } from './modules/shop/shopItem.component';
import { OrderComponent } from './modules/order/order.component';

export const routes: Routes =
[
    { path: '', component: HomeComponent}
    , { path: 'contact', component: ContactComponent}
    , { path: 'aboutMe', component: AboutMeComponent}
    , { path: 'relatedLinks', component: RelatedLinksComponent}
    , { path: 'priceList', component: PriceListComponent}
    , { path: 'myCourses', component: MyCoursesComponent , canActivate : [AuthGuard] }
    , { path : 'myCourses/:refNumber', component : CourseComponent, canActivate : [AuthGuard]}
    , { path : 'allCourses', component : AllCoursesComponent, canActivate : [AuthGuard] }
    , { path: 'games', component: GamesComponent}
    , { path: 'authenticate', component: AuthComponent}
    , { path: 'dashboard', component: DashboardComponent, canActivate : [AuthGuard]}
    , { path: 'accountSettings', component: AccountSettingsComponent, canActivate : [AuthGuard]}
    , { path: 'createCourse', component: CourseAddFormComponent, canActivate : [AuthGuard]}
    , { path: 'shop', component : ShopComponent}
    , { path : 'addShopItem', component : ShopItemFormComponent, canActivate : [AuthGuard]}
    , { path : 'addShopItem/:refNumber', component : ShopItemFormComponent, canActivate : [AuthGuard]}
    , { path : 'shopItem/:refNumber', component : ShopItemComponent}
    , { path : 'cart', component : CartComponent}
    , { path : 'cartSummary', component : CartSummaryComponent}
    , { path : 'discountCodes', component : DiscountCodesComponent, canActivate : [AuthGuard]}
    , { path : 'orders', component : OrdersComponent, canActivate : [AuthGuard]}
    , { path : 'allOrders', component : AllOrdersComponent, canActivate : [AuthGuard]}
    , { path : 'order/:refNumber', component : OrderComponent, canActivate : [AuthGuard]}
    , { path : 'students', component : StudentsComponent, canActivate : [AuthGuard]}
    , { path : 'tasks', component : TasksComponent, canActivate : [AuthGuard]}
    , { path : 'myShelf', component : ShelfComponent, canActivate : [AuthGuard]}
    , { path : 'fileManager', component : FileManagerComponent, canActivate : [AuthGuard]}
]