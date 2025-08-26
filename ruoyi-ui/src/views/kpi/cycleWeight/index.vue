<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" ref="queryRef">
      <el-form-item label="周期ID"><el-input-number v-model="queryParams.cycleId" :min="1"/></el-form-item>
      <el-form-item label="主体"><el-select v-model="queryParams.subjectType" clearable><el-option label="个人" value="person"/><el-option label="部门" value="dept"/><el-option label="中层" value="middle"/></el-select></el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"><el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['kpi:cycleWeight:add']">新增</el-button></el-col>
      <el-col :span="1.5"><el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['kpi:cycleWeight:edit']">修改</el-button></el-col>
      <el-col :span="1.5"><el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['kpi:cycleWeight:remove']">删除</el-button></el-col>
      <el-col :span="1.5"><el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['kpi:cycleWeight:export']">导出</el-button></el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column prop="id" label="ID" width="80"/>
      <el-table-column prop="cycleId" label="周期ID" width="100"/>
      <el-table-column prop="subjectType" label="主体" width="100"/>
      <el-table-column prop="raterPostId" label="评分职位ID" width="120"/>
      <el-table-column prop="weight" label="权重" width="100"/>
      <el-table-column prop="createTime" label="创建时间" width="180"/>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['kpi:cycleWeight:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['kpi:cycleWeight:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" :visible.sync="open" width="600px">
      <el-form ref="formRef" :model="form" label-width="120px">
        <el-form-item label="周期ID" prop="cycleId"><el-input-number v-model="form.cycleId" :min="1"/></el-form-item>
        <el-form-item label="主体" prop="subjectType"><el-select v-model="form.subjectType"><el-option label="个人" value="person"/><el-option label="部门" value="dept"/><el-option label="中层" value="middle"/></el-select></el-form-item>
        <el-form-item label="评分职位ID" prop="raterPostId"><el-input-number v-model="form.raterPostId" :min="1"/></el-form-item>
        <el-form-item label="权重" prop="weight"><el-input-number v-model="form.weight" :min="0" :max="100" :precision="2"/></el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open=false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCycleWeight, getCycleWeight, addCycleWeight, updateCycleWeight, delCycleWeight, exportCycleWeight } from '@/api/kpi/cycleWeight'
export default {
  name: 'KpiCycleWeight',
  data() {
    return { loading: false, list: [], total: 0, title: '', open: false, ids: [], single: true, multiple: true,
      queryParams: { pageNum: 1, pageSize: 10, cycleId: undefined, subjectType: undefined },
      form: { id: undefined, cycleId: undefined, subjectType: 'person', raterPostId: undefined, weight: 0 } }
  },
  created() { this.getList() },
  methods: {
    getList() { this.loading = true; listCycleWeight(this.queryParams).then(res => { this.list = res.rows; this.total = res.total; this.loading = false }) },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.$refs.queryRef.resetFields(); this.handleQuery() },
    handleSelectionChange(sel) { this.ids = sel.map(x => x.id); this.single = sel.length !== 1; this.multiple = !sel.length },
    handleAdd() { this.title='新增权重'; this.form = { id: undefined, cycleId: undefined, subjectType: 'person', raterPostId: undefined, weight: 0 }; this.open = true },
    handleUpdate(row) { const id = row.id || this.ids[0]; getCycleWeight(id).then(res => { this.form = res.data; this.title='修改权重'; this.open = true }) },
    submitForm() { (this.form.id ? updateCycleWeight : addCycleWeight)(this.form).then(()=>{ this.$message.success('操作成功'); this.open=false; this.getList() }) },
    handleDelete(row) { const ids = row.id ? row.id : this.ids; this.$modal.confirm('确认删除选中数据吗？').then(()=>delCycleWeight(ids)).then(()=>{ this.getList(); this.$message.success('删除成功') }) },
    handleExport() { exportCycleWeight(this.queryParams).then(res => { this.$download.name('评分权重.xlsx').blob(res) }) }
  }
}
</script>
