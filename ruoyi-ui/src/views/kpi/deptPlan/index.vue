<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" ref="queryRef">
      <el-form-item label="周期ID"><el-input-number v-model="queryParams.cycleId" :min="1"/></el-form-item>
      <el-form-item label="部门"><el-input-number v-model="queryParams.targetDeptId" :min="1"/></el-form-item>
      <el-form-item label="状态"><el-select v-model="queryParams.status" clearable><el-option label="草稿" value="draft"/><el-option label="待审" value="pending"/><el-option label="通过" value="approved"/><el-option label="驳回" value="rejected"/><el-option label="进行中" value="active"/><el-option label="已结束" value="finished"/></el-select></el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"><el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['kpi:deptPlan:add']">新增</el-button></el-col>
      <el-col :span="1.5"><el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['kpi:deptPlan:edit']">修改</el-button></el-col>
      <el-col :span="1.5"><el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['kpi:deptPlan:remove']">删除</el-button></el-col>
      <el-col :span="1.5"><el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['kpi:deptPlan:export']">导出</el-button></el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="cycleId" label="周期ID" width="100" />
      <el-table-column prop="targetDeptId" label="部门" width="100" />
      <el-table-column prop="status" label="状态" width="100" />
      <el-table-column prop="submitTime" label="提交时间" width="180" />
      <el-table-column prop="createTime" label="创建时间" width="180" />
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['kpi:deptPlan:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['kpi:deptPlan:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog :title="title" :visible.sync="open" width="700px">
      <el-form ref="formRef" :model="form" label-width="120px">
        <el-form-item label="周期ID" prop="cycleId"><el-input-number v-model="form.cycleId" :min="1"/></el-form-item>
        <el-form-item label="部门" prop="targetDeptId"><el-input-number v-model="form.targetDeptId" :min="1"/></el-form-item>
        <el-form-item label="状态" prop="status"><el-select v-model="form.status"><el-option label="草稿" value="draft"/><el-option label="待审" value="pending"/><el-option label="通过" value="approved"/><el-option label="驳回" value="rejected"/><el-option label="进行中" value="active"/><el-option label="已结束" value="finished"/></el-select></el-form-item>
        <el-form-item label="备注" prop="remark"><el-input v-model="form.remark" type="textarea"/></el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open=false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDeptPlan, getDeptPlan, addDeptPlan, updateDeptPlan, delDeptPlan, exportDeptPlan } from '@/api/kpi/deptPlan'
export default {
  name: 'KpiDeptPlan',
  data() {
    return { loading: false, list: [], total: 0, title: '', open: false, ids: [], single: true, multiple: true,
      queryParams: { pageNum: 1, pageSize: 10, cycleId: undefined, targetDeptId: undefined, status: undefined },
      form: { id: undefined, cycleId: undefined, targetDeptId: undefined, status: 'draft', remark: '' } }
  },
  created() { this.getList() },
  methods: {
    getList() { this.loading = true; listDeptPlan(this.queryParams).then(res => { this.list = res.rows; this.total = res.total; this.loading = false }) },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.$refs.queryRef.resetFields(); this.handleQuery() },
    handleSelectionChange(sel) { this.ids = sel.map(x => x.id); this.single = sel.length !== 1; this.multiple = !sel.length },
    handleAdd() { this.title='新增方案'; this.form = { id: undefined, cycleId: undefined, targetDeptId: undefined, status: 'draft', remark: '' }; this.open = true },
    handleUpdate(row) { const id = row.id || this.ids[0]; getDeptPlan(id).then(res => { this.form = res.data; this.title='修改方案'; this.open = true }) },
    submitForm() { (this.form.id ? updateDeptPlan : addDeptPlan)(this.form).then(()=>{ this.$message.success('操作成功'); this.open=false; this.getList() }) },
    handleDelete(row) { const ids = row.id ? row.id : this.ids; this.$modal.confirm('确认删除选中数据吗？').then(()=>delDeptPlan(ids)).then(()=>{ this.getList(); this.$message.success('删除成功') }) },
    handleExport() { exportDeptPlan(this.queryParams).then(res => { this.$download.name('部门考核方案.xlsx').blob(res) }) }
  }
}
</script>
