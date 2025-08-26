<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" ref="queryRef">
      <el-form-item label="分类名称">
        <el-input v-model="queryParams.name" placeholder="请输入分类名称" size="small" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['kpi:indicatorCategory:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['kpi:indicatorCategory:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['kpi:indicatorCategory:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['kpi:indicatorCategory:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="分类名称" />
      <el-table-column prop="parentId" label="父ID" width="100" />
      <el-table-column prop="sort" label="排序" width="80" />
      <el-table-column prop="status" label="状态" width="100" />
      <el-table-column prop="createTime" label="创建时间" width="180" />
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['kpi:indicatorCategory:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['kpi:indicatorCategory:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="formRef" :model="form" label-width="100px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="父分类ID" prop="parentId">
          <el-input-number v-model="form.parentId" :min="0" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" :min="0" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择">
            <el-option label="正常" value="0" />
            <el-option label="停用" value="1" />
          </el-select>
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
import { listIndicatorCategory, getIndicatorCategory, addIndicatorCategory, updateIndicatorCategory, delIndicatorCategory, exportIndicatorCategory } from '@/api/kpi/indicatorCategory'

export default {
  name: 'KpiIndicatorCategory',
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
      queryParams: { pageNum: 1, pageSize: 10, name: '' },
      form: { id: undefined, name: '', parentId: 0, sort: 0, status: '0', remark: '' }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listIndicatorCategory(this.queryParams).then(res => {
        this.list = res.rows
        this.total = res.total
        this.loading = false
      })
    },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.$refs.queryRef.resetFields(); this.handleQuery() },
    handleSelectionChange(selection) { this.ids = selection.map(x => x.id); this.single = selection.length !== 1; this.multiple = !selection.length },
    handleAdd() { this.title = '新增分类'; this.form = { id: undefined, name: '', parentId: 0, sort: 0, status: '0', remark: '' }; this.open = true },
    handleUpdate(row) { const id = row.id || this.ids[0]; getIndicatorCategory(id).then(res => { this.form = res.data; this.title='修改分类'; this.open = true }) },
    submitForm() {
      const api = this.form.id ? updateIndicatorCategory : addIndicatorCategory
      api(this.form).then(() => { this.$message.success('操作成功'); this.open=false; this.getList() })
    },
    handleDelete(row) { const ids = row.id ? row.id : this.ids; this.$modal.confirm('确认删除选中数据吗？').then(() => delIndicatorCategory(ids)).then(() => { this.getList(); this.$message.success('删除成功') }) },
    handleExport() { exportIndicatorCategory(this.queryParams).then(res => { this.$download.name('指标分类.xlsx').blob(res) }) }
  }
}
</script>
