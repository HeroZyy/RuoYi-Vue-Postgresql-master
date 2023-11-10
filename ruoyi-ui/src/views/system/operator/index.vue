<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="操作员性别" prop="sex">
        <el-input
          v-model="queryParams.sex"
          placeholder="请输入操作员性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作员姓名" prop="realname">
        <el-input
          v-model="queryParams.realname"
          placeholder="请输入操作员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作员手机" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入操作员手机"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作员公司名称" prop="company">
        <el-input
          v-model="queryParams.company"
          placeholder="请输入操作员公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作员公司地址" prop="addr">
        <el-input
          v-model="queryParams.addr"
          placeholder="请输入操作员公司地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作员类型,0表示超级管理员,1表示操作员" prop="optType">
        <el-input
          v-model="queryParams.optType"
          placeholder="请输入操作员类型,0表示超级管理员,1表示操作员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="头像" prop="avator">
        <el-input
          v-model="queryParams.avator"
          placeholder="请输入头像"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="email" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入email"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:operator:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:operator:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:operator:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:operator:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="operatorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="操作员id" align="center" prop="sid" />
      <el-table-column label="操作员性别" align="center" prop="sex" />
      <el-table-column label="操作员姓名" align="center" prop="realname" />
      <el-table-column label="操作员手机" align="center" prop="phone" />
      <el-table-column label="操作员公司名称" align="center" prop="company" />
      <el-table-column label="操作员公司地址" align="center" prop="addr" />
      <el-table-column label="操作员类型,0表示超级管理员,1表示操作员" align="center" prop="optType" />
      <el-table-column label="头像" align="center" prop="avator" />
      <el-table-column label="email" align="center" prop="email" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:operator:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:operator:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改操作员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="操作员性别" prop="sex">
          <el-input v-model="form.sex" placeholder="请输入操作员性别" />
        </el-form-item>
        <el-form-item label="操作员姓名" prop="realname">
          <el-input v-model="form.realname" placeholder="请输入操作员姓名" />
        </el-form-item>
        <el-form-item label="操作员手机" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入操作员手机" />
        </el-form-item>
        <el-form-item label="操作员公司名称" prop="company">
          <el-input v-model="form.company" placeholder="请输入操作员公司名称" />
        </el-form-item>
        <el-form-item label="操作员公司地址" prop="addr">
          <el-input v-model="form.addr" placeholder="请输入操作员公司地址" />
        </el-form-item>
        <el-form-item label="操作员类型,0表示超级管理员,1表示操作员" prop="optType">
          <el-input v-model="form.optType" placeholder="请输入操作员类型,0表示超级管理员,1表示操作员" />
        </el-form-item>
        <el-form-item label="头像" prop="avator">
          <el-input v-model="form.avator" placeholder="请输入头像" />
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="form.email" placeholder="请输入email" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOperator, getOperator, delOperator, addOperator, updateOperator } from "@/api/system/operator";

export default {
  name: "Operator",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 操作员表格数据
      operatorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sex: null,
        realname: null,
        phone: null,
        company: null,
        addr: null,
        optType: null,
        avator: null,
        email: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        realname: [
          { required: true, message: "操作员姓名不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "操作员手机不能为空", trigger: "blur" }
        ],
        optType: [
          { required: true, message: "操作员类型,0表示超级管理员,1表示操作员不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询操作员列表 */
    getList() {
      this.loading = true;
      listOperator(this.queryParams).then(response => {
        this.operatorList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        sid: null,
        sex: null,
        realname: null,
        phone: null,
        company: null,
        addr: null,
        optType: null,
        avator: null,
        email: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加操作员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sid = row.sid || this.ids
      getOperator(sid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改操作员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sid != null) {
            updateOperator(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOperator(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sids = row.sid || this.ids;
      this.$modal.confirm('是否确认删除操作员编号为"' + sids + '"的数据项？').then(function() {
        return delOperator(sids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/operator/export', {
        ...this.queryParams
      }, `operator_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
