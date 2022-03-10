<template>	<div >	
			<mt-tabbar  v-model="selected" fixed>		  <mt-tab-item id="1">			  <img slot="icon" src="../assets/场景管理.png" />相册			  </mt-tab-item>		  <mt-tab-item id="2">			  <img slot="icon" src="../assets/上传.png" />上传			  </mt-tab-item>		  <mt-tab-item id="3">			  <img slot="icon" src="../assets/我的.png" />我的			  </mt-tab-item>		</mt-tabbar> 				<!-- tab-container -->		<mt-tab-container v-model="selected" fixed>		  <mt-tab-container-item id="1" >			  <Albums></Albums>		  </mt-tab-container-item>		  <mt-tab-container-item id="2">				<Upload></Upload>		  </mt-tab-container-item>		  <mt-tab-container-item id="3">		  		<My></My>				  </mt-tab-container-item>		</mt-tab-container>
		</div></template><script>
import Mint from 'mint-ui';import My 	from './My.vue';import Albums from './Albums.vue';import Photos from './Photos.vue';import Upload from './Upload.vue';

export default{	name:"Main",	data(){		return{			selected:'1',			token:""		}	},	components:{		Albums:Albums,		My:My,		Photos:Photos,		Upload:Upload	}}</script><style>

</style>