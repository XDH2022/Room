import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "welcome",
            component: () => import("@/views/Welcome.vue"),
            children: [
                {
                    path: "",
                    name: "welcome-login",
                    component: () => import("@/components/welcome/LoginPage.vue")
                }, {
                    path: "register",
                    name: "welcome-register",
                    component: () => import("@/components/welcome/RegisterPage.vue")
                }, {
                    path: "forget",
                    name: "welcome-forget",
                    component: () => import("@/components/welcome/ForgetPage.vue")
                }
            ]
        },
        {
            path: "/room/:token?",
            name: "room",
            component: () => import("@/views/Room.vue"),
        }
    ]
})

export default router