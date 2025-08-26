<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" ref="queryRef">
      <el-form-item label="年份">
        <el-input-number v-model="queryParams.year" :min="2000" :max="2100" size="small" />
      </el-form-item>
      <el-form-item label="种类">
        <el-select v-model="queryParams.kind" clearable placeholder="请选择">
          <el-option label="部门" value="dept" />
          <el-option label="个人" value="person" />
          <el-option label="中层" value="middle" />
        </el-select>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="queryParams.name" placeholder="指标名称" size="small" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['kpi:indicator:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['kpi:indicator:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['kpi:indicator:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['kpi:indicator:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="year" label="年份" width="90" />
      <el-table-column prop="kind" label="种类" width="100" />
      <el-table-column prop="categoryId" label="分类ID" width="100" />
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="scoreFull" label="满分" width="100" />
      <el-table-column prop="status" label="状态" width="100" />
      <el-table-column prop="createTime" label="创建时间" width="180" />
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['kpi:indicator:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['kpi:indicator:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog :title="title" :visible.sync="open" width="700px">
      <el-form ref="formRef" :model="form" label-width="120px">
        <el-form-item label="年份" prop="year">
          <el-input-number v-model="form.year" :min="2000" :max="2100" />
        </el-form-item>
        <el-form-item label="种类" prop="kind">
          <el-select v-model="form.kind" placeholder="请选择">
            <el-option label="部门" value="dept" />
            <el-option label="个人" value="person" />
            <el-option label="中层" value="middle" />
          </el-select>
        </el-form-item>
        <el-form-item label="分类ID" prop="categoryId">
          <el-input-number v-model="form.categoryId" :min="0" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="评分标准" prop="scoringStandard">
          <el-input v-model="form.scoringStandard" type="textarea" />
        </el-form-item>
        <el-form-item label="评分来源" prop="source">
          <el-input v-model="form.source" />
        </el-form-item>
        <el-form-item label="满分" prop="scoreFull">
          <el-input-number v-model="form.scoreFull" :min="0" :precision="2" :step="1" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择">
            <el-option label="正常" value="0" />
            <el-option label="停用" value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="说明" prop="description">
          <el-input v-model="form.description" type="textarea" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open=false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listIndicator, getIndicator, addIndicator, updateIndicator, delIndicator, exportIndicator } from '@/api/kpi/indicator'

export default {
  name: 'KpiIndicator',
  data() {
    return {
      loading: false,
      list: [],
      total: 0,
      title: '',
      open: false,
      ids: [],
      single: true,
      multiple: true,
      queryParams: { pageNum: 1, pageSize: 10, year: undefined, kind: undefined, name: '' },
      form: { id: undefined, year: new Date().getFullYear(), kind: 'person', categoryId: 0, name: '', scoringStandard: '', source: '', scoreFull: 100, status: '0', description: '', remark: '' }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listIndicator(this.queryParams).then(res => {
        this.list = res.rows
        this.total = res.total
        this.loading = false
      })
    },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.$refs.queryRef.resetFields(); this.handleQuery() },
    handleSelectionChange(selection) { this.ids = selection.map(x => x.id); this.single = selection.length !== 1; this.multiple = !selection.length },
    handleAdd() { this.title = '新增指标'; this.form = { id: undefined, year: new Date().getFullYear(), kind: 'person', categoryId: 0, name: '', scoringStandard: '', source: '', scoreFull: 100, status: '0', description: '', remark: '' }; this.open = true },
    handleUpdate(row) { const id = row.id || this.ids[0]; getIndicator(id).then(res => { this.form = res.data; this.title='修改指标'; this.open = true }) },
    submitForm() {
      const api = this.form.id ? updateIndicator : addIndicator
      api(this.form).then(() => { this.$message.success('操作成功'); this.open=false; this.getList() })
    },
    handleDelete(row) { const ids = row.id ? row.id : this.ids; this.$modal.confirm('确认删除选中数据吗？').then(() => delIndicator(ids)).then(() => { this.getList(); this.$message.success('删除成功') }) },
    handleExport() { exportIndicator(this.queryParams).then(res => { this.$download.name('指标库.xlsx').blob(res) }) }
  }
}
</script>
