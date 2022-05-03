<template>
  <div style="display: flex;justify-content: center;padding: 20px;width: 100%">
    <el-select v-model="value" class="m-2"  style="width: 125px;padding-right: 20px">
      <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      />
    </el-select>
    <el-input v-model="input" placeholder="" style="width: 300px;margin-right: 20px" clearable/>
    <el-button type="primary" style="margin-top: 5px" @click="load">
      <el-icon style="vertical-align: middle;">
        <search />
      </el-icon>
      <span style="vertical-align: middle;"> Search </span>
    </el-button>
  </div>
  <div style="display: flex;justify-content: center">
    <el-table :data="sessionData"
              style="width: 90%; border: groove"
              border
              stripe
              class="session"
              :header-cell-style="{borderColor:'grey', fontSize: '14px', backgroundColor: '#87CEFA',textAlign:'center'}"
              :cell-style="{borderColor: 'grey',textAlign: 'center',color: 'black'}"

    >
      <el-table-column fixed prop="id" label="编号" min-width="80"/>
      <el-table-column fixed prop="movieName" label="电影名称" min-width="300px" />
      <el-table-column prop="img" label="宣传图片" min-width="100" >
        <template #default="cinema">
          <img :src="cinema.row.img" style="height: 92px;width: 69px">
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话" min-width="100"/>
      <el-table-column prop="area" label="地区" min-width="100"/>
      <el-table-column prop="address" label="地址" min-width="600px" />

      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="cinemaList">
          <el-button type="text" style="height: 16px" @click="reCinema(cinemaList.row)">
            <el-icon style="vertical-align: middle;">
              <Edit />
            </el-icon>
          </el-button>
          <el-popconfirm
              confirm-button-text="确定"
              cancel-button-text="取消"
              :icon="InfoFilled"
              icon-color="red"
              title="确认删除该行数据吗？"
              @confirm="delCinema(cinemaList.row.id)"
          >
            <template #reference>
              <el-button type="text" style="height: 16px">
                <el-icon style="vertical-align: middle;">
                  <Delete />
                </el-icon>
              </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <div class="demo-pagination-block" style="display: flex;justify-content: center;padding-top: 20px">
    <el-pagination
        v-model:currentPage="currentPage"
        layout="total,prev, pager, next, jumper"
        :total="total"
        v-model:page-size="pageSize"
        @current-change="handleCurrentChange"
        class="page"
    />
  </div>
  <div>
    <el-dialog v-model="reCinemaDialog"  width="500px" center>
      <el-form :model="cinemaForm" ref="cinemaForm" label-width="120px" size="large" class="cinemaForm" :rules="cinemaRules" >
        <el-form-item label="宣传图片" prop="img">
          <el-upload
              class="avatar-uploader"
              action="http://localhost:81/files/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
            <img v-if="cinemaForm.img" :src="cinemaForm.img" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="影院名称" prop="cinemaName">
          <el-input v-model="cinemaForm.cinemaName" style="width: 500px"/>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="cinemaForm.phone" style="width: 500px"/>
        </el-form-item>
        <el-form-item label="地区" prop="area">
          <el-input v-model="cinemaForm.area" style="width: 500px"/>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="cinemaForm.address" type="textarea" style="border: outset"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="reCinema(this.reback)">还原</el-button>
        <el-button type="primary" @click="saveReCinema">保存</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
  </div>
</template>

<script>
export default {
  name: "ManageSession"
}
</script>

<style scoped>

</style>