<template>
  <div style="width: 100vh">
    <div style="display: flex;justify-content: center;padding: 20px;width: 100%">
      <el-select v-model="value" class="m-2"  style="width: 125px;padding-right: 20px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <el-input v-model="input" placeholder="" style="width: 300px;margin-right: 20px" />
      <el-button type="primary" style="margin-top: 5px">
        <el-icon style="vertical-align: middle;">
          <search />
        </el-icon>
        <span style="vertical-align: middle;"> Search </span>
      </el-button>
    </div>
    <div style="display: flex;justify-content: center">
      <el-table :data="managerData"
                style="width: 90%;"
                border
                stripe
                class="manager"
                :header-cell-style="{borderColor:'grey', fontSize: '14px', backgroundColor: '#87CEFA',textAlign:'center'}"
                :cell-style="{borderColor: 'grey',textAlign: 'center',color: 'black'}"

      >
        <el-table-column fixed prop="id" label="编号" min-width="60"/>
        <el-table-column fixed prop="username" label="用户名" min-width="100" />
        <el-table-column prop="password" label="密码" min-width="150" />
        <el-table-column prop="sex" label="性别" min-width="60" />
        <el-table-column prop="birthday" label="出生日期" min-width="120" />
        <el-table-column prop="ascription" label="归属" min-width="120" />
        <el-table-column prop="phonenum" label="电话号码" width="120" />
        <el-table-column prop="address" label="地址" width="600" />

        <el-table-column fixed="right" label="Operations" width="120">
          <template #default>
            <el-button type="text" style="height: 16px">
              <el-icon style="vertical-align: middle;">
                <Edit />
              </el-icon>
            </el-button>
            <el-button type="text" style="height: 16px">
              <el-icon style="vertical-align: middle;">
                <Delete />
              </el-icon>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="demo-pagination-block" style="display: flex;justify-content: center;padding-top: 20px">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, prev, pager, next, jumper"
          :total="400"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          class="page"
      />
    </div>
  </div>

</template>

<script>
import {Search,Edit,Delete} from "@element-plus/icons";

export default {
  name: "ManageManager",
  components:{
    Search,
    Edit,
    Delete,
  },
  data(){
    return{
      value: 'id',
      options:[
        {
          value:'id',
          label:'编号',
        },
        {
          value: 'username',
          label: '用户名',
        },
        {
          value: 'phonenum',
          label: '电话号码',
        },
        {
          value: 'ascription',
          label: '归属',
        }
      ],
      managerData:[
        {
          id: '1',
          username: 'Tom',
          date: '2016-05-03',

          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
        },
      ]
    }
  },
  methods:{
    handleClick(){

    },
  }
}

</script>

<style scoped>
.manager{
}




</style>