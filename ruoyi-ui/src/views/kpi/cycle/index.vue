<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" ref="queryRef">
      <el-form-item label="类型"><el-select v-model="queryParams.cycleType" clearable><el-option label="季度" value="quarter"/><el-option label="半年" value="half"/><el-option label="年度" value="year"/><el-option label="自定义" value="custom"/></el-select></el-form-item>
      <el-form-item label="年份"><el-input-number v-model="queryParams.year" :min="2000" :max="2100" size="small"/></el-form-item>
      <el-form-item label="状态"><el-select v-model="queryParams.status" clearable><el-option label="未开始" value="not_started"/><el-option label="进行中" value="ongoing"/><el-option label="已结束" value="finished"/></el-select></el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"><el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['kpi:cycle:add']">新增</el-button></el-col>
      <el-col :span="1.5"><el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['kpi:cycle:edit']">修改</el-button></el-col>
      <el-col :span="1.5"><el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['kpi:cycle:remove']">删除</el-button></el-col>
      <el-col :span="1.5"><el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['kpi:cycle:export']">导出</el-button></el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column prop="id" label="ID" width="80"/>
      <el-table-column prop="cycleType" label="类型" width="100"/>
      <el-table-column prop="year" label="年份" width="90"/>
      <el-table-column prop="startTime" label="开始时间" width="180"/>
      <el-table-column prop="endTime" label="结束时间" width="180"/>
      <el-table-column prop="status" label="状态" width="100"/>
      <el-table-column prop="createTime" label="创建时间" width="180"/>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['kpi:cycle:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['kpi:cycle:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" :visible.sync="open" width="700px">
      <el-form ref="formRef" :model="form" label-width="120px">
        <el-form-item label="类型" prop="cycleType"><el-select v-model="form.cycleType"><el-option label="季度" value="quarter"/><el-option label="半年" value="half"/><el-option label="年度" value="year"/><el-option label="自定义" value="custom"/></el-select></el-form-item>
        <el-form-item label="年份" prop="year"><el-input-number v-model="form.year" :min="2000" :max="2100"/></el-form-item>
        <el-form-item label="开始时间" prop="startTime"><el-date-picker v-model="form.startTime" type="datetime" placeholder="选择时间"/></el-form-item>
        <el-form-item label="结束时间" prop="endTime"><el-date-picker v-model="form.endTime" type="datetime" placeholder="选择时间"/></el-form-item>
        <el-form-item label="状态" prop="status"><el-select v-model="form.status"><el-option label="未开始" value="not_started"/><el-option label="进行中" value="ongoing"/><el-option label="已结束" value="finished"/></el-select></el-form-item>
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
import { listCycle, getCycle, addCycle, updateCycle, delCycle, exportCycle } from '@/api/kpi/cycle'
export default {
  name: 'KpiCycle',
  data() {
    return { loading: false, list: [], total: 0, title: '', open: false, ids: [], single: true, multiple: true,
      queryParams: { pageNum: 1, pageSize: 10, cycleType: undefined, year: undefined, status: undefined },
      form: { id: undefined, cycleType: 'quarter', year: new Date().getFullYear(), startTime: '', endTime: '', status: 'not_started', remark: '' } }
  },
  created() { this.getList() },
  methods: {
    getList() { this.loading = true; listCycle(this.queryParams).then(res => { this.list = res.rows; this.total = res.total; this.loading = false }) },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.$refs.queryRef.resetFields(); this.handleQuery() },
    handleSelectionChange(sel) { this.ids = sel.map(x => x.id); this.single = sel.length !== 1; this.multiple = !sel.length },
    handleAdd() { this.title='新增周期'; this.form = { id: undefined, cycleType: 'quarter', year: new Date().getFullYear(), startTime: '', endTime: '', status: 'not_started', remark: '' }; this.open = true },
    handleUpdate(row) { const id = row.id || this.ids[0]; getCycle(id).then(res => { this.form = res.data; this.title='修改周期'; this.open = true }) },
    submitForm() { (this.form.id ? updateCycle : addCycle)(this.form).then(()=>{ this.$message.success('操作成功'); this.open=false; this.getList() }) },
    handleDelete(row) { const ids = row.id ? row.id : this.ids; this.$modal.confirm('确认删除选中数据吗？').then(()=>delCycle(ids)).then(()=>{ this.getList(); this.$message.success('删除成功') }) },
    handleExport() { exportCycle(this.queryParams).then(res => { this.$download.name('考核周期.xlsx').blob(res) }) }
  }
}
</script>
