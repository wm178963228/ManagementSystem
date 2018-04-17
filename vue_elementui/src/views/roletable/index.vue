<template>
  <div class="app-container">
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column label="Name">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.state | statusFilter">{{scope.row.state | statusFilter2}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="Create_time" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {getUserList} from '@/api/user'

  export default {
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
        })
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
      }
    },
    created() {
      this.fetchData2()
    },
    methods: {
      fetchData2() {
        this.listLoading = true
        getUserList(this.listQuery).then(response => {
          this.list = response.data.userList
          this.total = response.data.total
          this.listLoading = false
        })
      }
    }
  }
</script>
