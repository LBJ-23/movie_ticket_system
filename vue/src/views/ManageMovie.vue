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
      <el-input v-model="input" placeholder="" style="width: 300px;margin-right: 20px" clearable/>
      <el-button type="primary" style="margin-top: 5px" @click="load">
        <el-icon style="vertical-align: middle;">
          <search />
        </el-icon>
        <span style="vertical-align: middle;"> Search </span>
      </el-button>
    </div>
    <div style="display: flex;justify-content: center">
      <el-table :data="movieData"
                style="width: 90%; border: groove"
                border
                stripe
                class="movie"
                :header-cell-style="{borderColor:'grey', fontSize: '14px', backgroundColor: '#87CEFA',textAlign:'center'}"
                :cell-style="{borderColor: 'grey',textAlign: 'center',color: 'black'}"

      >
        <el-table-column fixed prop="id" label="编号" min-width="80"/>
        <el-table-column fixed prop="movieName" label="电影名称" min-width="150" />
        <el-table-column prop="img" label="电影海报" min-width="100" >
          <template #default="movie">
            <img :src="movie.row.img" style="height: 92px;width: 69px">
          </template>
        </el-table-column>
        <el-table-column prop="duration" label="电影时长" min-width="100"/>
        <el-table-column prop="type" label="电影类别" min-width="150" />
        <el-table-column prop="releasedTime" label="上映日期" min-width="120" />
        <el-table-column prop="actor" label="演员" min-width="150" />
        <el-table-column prop="director" label="导演" width="100" />
        <el-table-column prop="source" label="产地" width="100" />
        <el-table-column prop="introduce" label="电影简介" width="1000" />

        <el-table-column fixed="right" label="Operations" width="120">
          <template #default="movieList">
            <el-button type="text" style="height: 16px" @click="reMovie(movieList.row)">
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
                @confirm="delMovie(movieList.row.id)"
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
      <el-dialog v-model="reMovieDialog"  width="500px" center>
        <el-form :model="movieForm" ref="movieForm" label-width="auto" size="middle" class="managerForm" :rules="movieRules">
          <el-form-item label="编号" prop="id">
            <p v-text="movieForm.id" style="padding-left: 1vh"></p>
          </el-form-item>
          <el-form-item label="海报" prop="img">
            <el-upload
                class="avatar-uploader"
                action="http://localhost:81/files/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
            >
              <img v-if="movieForm.img" :src="movieForm.img" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
          </el-form-item>
          <el-form-item label="电影名称" prop="movieName">
            <el-input v-model="movieForm.movieName" style="width: 500px"/>
          </el-form-item>
          <el-form-item label="放映时长" prop="duration">
            <el-input v-model="movieForm.duration"/>
          </el-form-item>
          <el-form-item label="电影类型" prop="type" style="width: 500px">
            <el-checkbox-group v-model="movieForm.type">
              <el-checkbox label="动作" />
              <el-checkbox label="喜剧" />
              <el-checkbox label="科幻" />
              <el-checkbox label="悬疑" />
              <el-checkbox label="恐怖" />
              <el-checkbox label="犯罪" />
              <el-checkbox label="冒险" />
              <el-checkbox label="战争" />
              <el-checkbox label="剧情" />
              <el-checkbox label="灾难" />
              <el-checkbox label="爱情" />
              <el-checkbox label="动画" />
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="上映日期" prop="releasedTime">
            <el-col :span="11">
              <el-date-picker
                  v-model="movieForm.releasedTime"
                  type="date"
                  placeholder="请选择上映日期"
                  style="width: 100%"
              />
            </el-col>
          </el-form-item>

          <el-form-item label="导演" prop="director">
            <el-input v-model="movieForm.director" />
          </el-form-item>
          <el-form-item label="主演" prop="actor">
            <el-input v-model="movieForm.actor" />
          </el-form-item>
          <el-form-item label="电影产地" prop="source">
            <el-input v-model="movieForm.source" />
          </el-form-item>
          <el-form-item label="电影简介" prop="introduce">
            <el-input v-model="movieForm.introduce" type="textarea" style="border: outset"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="reMovie(this.reback)">还原</el-button>
        <el-button type="primary" @click="saveReMovie">保存</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {Search,Edit,Delete,InfoFilled,Plus} from "@element-plus/icons";
import request from "@/utils/request";
import dayjs from "dayjs";
import {ElMessage} from "element-plus";
export default {
  name: "managerMovie",
  components:{
    Search,
    Edit,
    Delete,
    Plus
  },
  created() {
    this.load()
  },
  data() {
    return {
      value: 'id',
      pageSize: 5,
      currentPage: 1,
      total: 0,
      input: '',
      reMovieDialog: false,
      movieForm: {},
      reback: {},
      InfoFilled,
      movieData: [],

      options: [
        {
          value: 'id',
          label: '编号',
        },
        {
          value: 'movie_name',
          label: '电影名称',
        },
        {
          value: 'type',
          label: '类别',
        },
        {
          value: 'actor',
          label: '主演',
        },
        {
          value: 'director',
          label: '导演',
        }
      ],
    }
  },
  methods:{
    load(){
      console.log(this.movieForm)
      request.get("/movies/movieFindPage",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          type: this.value,
          search: this.input,
        }

      }).then(res =>{
        console.log(res)
        this.movieData = res.data.records
        this.total = res.data.total
      })
    },
    // 改变当前页码数触发
    handleCurrentChange(){
      this.load()
    },
    // 修改数据
    reMovie(row){
      this.reMovieDialog = true
      this.movieForm = JSON.parse(JSON.stringify(row))
      this.movieForm.type = this.movieForm.type.split(",")
      this.reback = JSON.parse(JSON.stringify(row))
    },
    saveReMovie(){
      this.$refs.movieForm.validate().then(()=>{
        this.movieForm.releasedTime = dayjs(new Date(this.movieForm.releasedTime)).format(' YYYY-MM-DD HH:mm:ss').toString()
        if (this.movieForm.type) {undefined
          this.movieForm.type = this.movieForm.type.join(",");
        }
        console.log("in")
        request.put("/movies/updateMovie",this.movieForm).then(res => {
          console.log(res)
          this.reManagerDialog = false
          if(res.code == '200'){
            ElMessage({
              showClose: true,
              type: "success",
              message: "修改数据成功！",
              center: true,
            })
          }
          else {
            ElMessage({
              showClose: true,
              type: "error",
              message: "修改数据失败！",
              center: true,
            })
          }
          this.load()
        })
      }).catch(() => {
        ElMessage({
          type: 'error',
          message: '所填数据有误，请重新确认！',
          showClose: true,
        })
      })
    },
    // 删除数据
    delMovie(id){
      console.log(id)
      request.delete("/movies/delMovie/" +  id).then(res => {
        if(res.code == '200'){
          ElMessage({
            showClose: true,
            type: "success",
            message: "删除数据成功！",
            center: true,
          })
        }
        else {
          ElMessage({
            showClose: true,
            type: "error",
            message: "删除数据失败！",
            center: true,
          })
        }
        this.load()
      })
    },
    handleAvatarSuccess(res){
      this.movieForm.img = res;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt1M = file.size / 1024 / 1024 < 1;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG或PNG 格式!');
      }
      if (!isLt1M) {
        this.$message.error('上传头像图片大小不能超过 1MB!');
      }
      return isJPG && isLt1M;
    },
  }
}
</script>

<style scoped>

/*上传头像*/
.avatar-uploader .avatar {
  width: 60px;
  height: 80px;
  display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 3px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 80px;
  height: 80px;
  text-align: center;
}


.el-form-item__label {
  font-size: 20px;
}
.el-radio__input{
  border-radius: 50%;
  border: 1px ridge white;
}
</style>