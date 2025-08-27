<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" ref="queryRef">
      <el-form-item label="类型"><el-select v-model="queryParams.planType" clearable><el-option label="个人" value="person"/><el-option label="部门" value="dept"/></el-select></el-form-item>
      <el-form-item label="状态"><el-select v-model="queryParams.status" clearable><el-option label="已生成" value="generated"/><el-option label="已确认" value="confirmed"/></el-select></el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="handleQuery">搜索</el-button>
        <el-button size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"><el-button type="warning" plain size="mini" @click="handleExport" v-hasPermi="['kpi:result:export']">导出</el-button></el-col>
    </el-row>

    <el-table v-loading="loading" :data="list">
      <el-table-column prop="id" label="ID" width="80"/>
      <el-table-column prop="planType" label="类型" width="80"/>
      <el-table-column prop="planId" label="计划ID" width="100"/>
      <el-table-column prop="targetUserId" label="目标用户" width="100"/>
      <el-table-column prop="targetDeptId" label="目标部门" width="100"/>
      <el-table-column prop="finalScore" label="最终得分" width="120"/>
      <el-table-column prop="completeRatio" label="完成比例(%)" width="120"/>
      <el-table-column prop="status" label="状态" width="100"/>
      <el-table-column prop="generateTime" label="生成时间" width="180"/>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleSign(scope.row)" v-hasPermi="['kpi:resultSign:add']" v-if="scope.row.status==='generated'">签字确认</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
  </div>
</template>

<script>
import { listResult, exportResult, signResult } from '@/api/kpi/result'
export default {
  name: 'KpiResult',
  data() {
    return { loading: false, list: [], total: 0,
      queryParams: { pageNum: 1, pageSize: 10, planType: undefined, status: undefined } }
  },
  created() { this.getList() },
  methods: {
    getList() { this.loading = true; listResult(this.queryParams).then(res => { this.list = res.rows; this.total = res.total; this.loading = false }) },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.$refs.queryRef && this.$refs.queryRef.resetFields(); this.handleQuery() },
    handleExport() { exportResult(this.queryParams).then(res => { this.$download.name('考核结果.xlsx').blob(res) }) },
    handleSign(row) { this.$modal.confirm('确认签字确认该结果吗？').then(()=>signResult(row.id)).then(()=>{ this.$message.success('已签字确认'); this.getList() }) }
  }
}
</script>
