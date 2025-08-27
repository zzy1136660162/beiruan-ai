<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" ref="queryRef">
      <el-form-item label="状态"><el-select v-model="queryParams.status" clearable><el-option label="待完成" value="pending"/><el-option label="已完成" value="done"/><el-option label="已超期" value="expired"/></el-select></el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="handleQuery">搜索</el-button>
        <el-button size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-switch v-model="onlyMine" active-text="只看我的" @change="getList" />
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="list">
      <el-table-column prop="id" label="ID" width="80"/>
      <el-table-column prop="planType" label="类型" width="80"/>
      <el-table-column prop="planId" label="计划ID" width="100"/>
      <el-table-column prop="planItemId" label="项ID" width="100"/>
      <el-table-column prop="targetUserId" label="目标用户" width="100"/>
      <el-table-column prop="targetDeptId" label="目标部门" width="100"/>
      <el-table-column prop="deadline" label="截止时间" width="180"/>
      <el-table-column prop="status" label="状态" width="100"/>
      <el-table-column prop="finishTime" label="完成时间" width="180"/>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="openScore(scope.row)" v-if="scope.row.status==='pending'" v-hasPermi="['kpi:score:add']">评分</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog :title="'提交评分 - 任务#'+(scoreForm.taskId||'')" :visible.sync="scoreOpen" width="500px">
      <el-form :model="scoreForm" label-width="100px">
        <el-form-item label="得分"><el-input-number v-model="scoreForm.score" :min="0" :max="100" :precision="2"/></el-form-item>
        <el-form-item label="评语"><el-input v-model="scoreForm.comment" type="textarea"/></el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="scoreOpen=false">取 消</el-button>
        <el-button type="primary" @click="submitScore">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEvalTask, listMyEvalTask, addScore } from '@/api/kpi/evalTask'
export default {
  name: 'KpiEvalTask',
  data() {
    return { loading: false, list: [], total: 0,
      queryParams: { pageNum: 1, pageSize: 10, status: 'pending' },
      onlyMine: true,
      scoreOpen: false, scoreForm: { taskId: undefined, score: 0, comment: '' }
    }
  },
  created() { this.getList() },
  methods: {
    getList() {
      this.loading = true
      const fn = this.onlyMine ? listMyEvalTask : listEvalTask
      fn(this.queryParams).then(res => { this.list = res.rows; this.total = res.total; this.loading = false })
    },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.$refs.queryRef && this.$refs.queryRef.resetFields(); this.handleQuery() },
    openScore(row) { this.scoreForm = { taskId: row.id, score: 0, comment: '' }; this.scoreOpen = true },
    submitScore() {
      addScore(this.scoreForm).then(() => { this.$message.success('提交成功'); this.scoreOpen=false; this.getList() })
    }
  }
}
</script>
