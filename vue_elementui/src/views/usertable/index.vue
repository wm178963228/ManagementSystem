<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search">
        {{$t('table.search')}}
      </el-button>
      <div class="fr">
        <el-button class="filter-item" type="primary" v-waves icon="el-icon-refresh">
          {{$t('table.refresh')}}
        </el-button>
        <el-button class="filter-item" @click="handleCreate" type="primary" v-waves icon="el-icon-edit">
          {{$t('table.add')}}
        </el-button>
        <el-button class="filter-item" type="primary" v-waves icon="el-icon-download">
          {{$t('table.export')}}
        </el-button>
      </div>
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column :label="$t('table.id')" align="center">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.username')" align="center">
        <template slot-scope="scope">
          {{scope.row.username}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.state')" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.state | statusFilter">{{scope.row.state | statusFilter2}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.createTime')" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
          <el-button v-if="scope.row.status!='deleted'" size="mini" type="danger"
                     @click="handleModifyStatus(scope.row,'deleted')">{{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--<div class="pagination-container">-->
    <!--<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"-->
    <!--:current-page="listQuery.page" :page-sizes="[10,20,30,50]" :page-size="listQuery.limit"-->
    <!--layout="total, sizes, prev, pager, next, jumper" :total="total">-->
    <!--</el-pagination>-->
    <!--</div>-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="right" label-width="70px">
        <el-col :span="12">
          <el-form-item :label="$t('table.roleid')" prop="roleid">
            <el-input v-model="temp.roleid" style="width:200px"></el-input>
          </el-form-item>
          <el-form-item :label="$t('table.username')" prop="username">
            <el-input v-model="temp.username" style="width:200px"></el-input>
          </el-form-item>
          <el-form-item :label="$t('table.state')" prop="state">
            <el-select class="filter-item" v-model="temp.type" :placeholder="$t('table.pleaseSelect')"
                       style="width:200px">
              <el-option :label="item.display_name" v-for="item in  calendarStateOptions"
                         :key="item.key" :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item :label="$t('table.avatar')" prop="avatar">
            <el-input v-model="temp.avatar" style="width:200px"></el-input>
          </el-form-item>
          <el-form-item :label="$t('table.password')" prop="password">
            <el-input v-model="temp.password" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer" style="margin-top:150px;">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
        <el-button v-else type="primary">{{$t('table.confirm')}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {getUserList, addUser} from '@/api/user'
  import waves from '@/directive/waves' // 水波纹指令
  import ElCol from 'element-ui/packages/col/src/col'

  const calendarStateOptions = [
    {key: '1', display_name: '启用'},
    {key: '2', display_name: '禁用'},
    {key: '3', display_name: '删除'}
  ]
  // arr to obj ,such as { CN : "China", US : "USA" }
  const calendarStateKeyValue = calendarStateOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.display_name
    return acc
  }, {})

  export default {
    components: {ElCol},
    directives: {
      waves
    },
    data() {
      return {
        list: null,
        total: null,
        listLoading: true,
        listQuery: JSON.stringify({
          page: 1,
          limit: 10,
          name: undefined,
          state: 1
        }),
        calendarStateOptions,
        temp: {
          roleid: 1,
          username: '',
          loginname: '',
          password: '',
          avatar: '',
          state: 1
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create'
        },
        rules: {
          username: [{required: true, message: 'username is required', trigger: 'change'}]
        }
      }
    },
    filters: {
      statusFilter(status) {
        const statusMap = {
          1: 'success',
          2: 'danger'
        }
        return statusMap[status]
      },
      statusFilter2(status) {
        const statusMap = {
          1: '启用',
          2: '禁用'
        }
        return statusMap[status]
      },
      typeFilter(type) {
        return calendarStateKeyValue[type]
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getUserList(this.listQuery).then(response => {
          this.list = response.data.userList
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchData()
      },
      resetTemp() {
        this.temp = {
          roleid: 1,
          username: '',
          loginname: '',
          password: '',
          avatar: '',
          state: 1
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      handleUpdate(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            addUser(JSON.stringify(this.temp)).then(() => {
              this.list.unshift(this.temp)
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      }
    }
  }
</script>
