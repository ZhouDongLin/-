import Vue from 'vue';import ElementUI from 'element-ui';import 'element-ui/lib/theme-chalk/index.css';
import 'vant/lib/index.css';import vant from 'vant';import App from './App.vue';
import axios from 'axios';
import VueAxios from 'vue-axios'
import Mint from 'mint-ui';
import 'mint-ui/lib/style.css'

Vue.use(Mint);
Vue.use(vant);

Vue.use(VueAxios,axios)Vue.config.productionTip = falsenew Vue({
		el: '#app',    render: h => h(App),}).$mount('#app')