<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会员姓名" prop="realname">
        <el-input
          v-model="queryParams.realname"
          placeholder="请输入会员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员手机" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入会员手机"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员公司名称" prop="company">
        <el-input
          v-model="queryParams.company"
          placeholder="请输入会员公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员公司地址" prop="addr">
        <el-input
          v-model="queryParams.addr"
          placeholder="请输入会员公司地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员类型" prop="memberType">
        <el-input
          v-model="queryParams.memberType"
          placeholder="请输入会员类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商铺主对应的商铺id" prop="shopId">
        <el-input
          v-model="queryParams.shopId"
          placeholder="请输入商铺主对应的商铺id"
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
      <el-form-item label="职位" prop="job">
        <el-input
          v-model="queryParams.job"
          placeholder="请输入职位"
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
          v-hasPermi="['system:member:add']"
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
          v-hasPermi="['system:member:edit']"
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
          v-hasPermi="['system:member:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:member:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="memberList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="会员id" align="center" prop="sid" />
      <el-table-column label="会员性别" align="center" prop="sex" />
      <el-table-column label="会员姓名" align="center" prop="realname" />
      <el-table-column label="会员手机" align="center" prop="phone" />
      <el-table-column label="会员公司名称" align="center" prop="company" />
      <el-table-column label="会员公司地址" align="center" prop="addr" />
      <el-table-column label="会员类型" align="center" prop="memberType" />
      <el-table-column label="商铺主对应的商铺id" align="center" prop="shopId" />
      <el-table-column label="头像" align="center" prop="avator" />
      <el-table-column label="email" align="center" prop="email" />
      <el-table-column label="职位" align="center" prop="job" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:member:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:member:remove']"
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

    <!-- 添加或修改会员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会员姓名" prop="realname">
          <el-input v-model="form.realname" placeholder="请输入会员姓名" />
        </el-form-item>
        <el-form-item label="会员手机" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入会员手机" />
        </el-form-item>
        <el-form-item label="会员公司名称" prop="company">
          <el-input v-model="form.company" placeholder="请输入会员公司名称" />
        </el-form-item>
        <el-form-item label="会员公司地址" prop="addr">
          <el-input v-model="form.addr" placeholder="请输入会员公司地址" />
        </el-form-item>
        <el-form-item label="会员类型" prop="memberType">
          <el-input v-model="form.memberType" placeholder="请输入会员类型" />
        </el-form-item>
        <el-form-item label="商铺主对应的商铺id" prop="shopId">
          <el-input v-model="form.shopId" placeholder="请输入商铺主对应的商铺id" />
        </el-form-item>
        <el-form-item label="头像" prop="avator">
          <el-input v-model="form.avator" placeholder="请输入头像" />
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="form.email" placeholder="请输入email" />
        </el-form-item>
        <el-form-item label="职位" prop="job">
          <el-input v-model="form.job" placeholder="请输入职位" />
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
import { listMember, getMember, delMember, addMember, updateMember } from "@/api/system/member";

export default {
  name: "Member",
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
      // 会员表格数据
      memberList: [],
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
        memberType: null,
        shopId: null,
        avator: null,
        email: null,
        job: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        realname: [
          { required: true, message: "会员姓名不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "会员手机不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询会员列表 */
    getList() {
      this.loading = true;
      listMember(this.queryParams).then(response => {
        this.memberList = response.rows;
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
        memberType: null,
        shopId: null,
        avator: null,
        email: null,
        job: null
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
      this.title = "添加会员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sid = row.sid || this.ids
      getMember(sid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sid != null) {
            updateMember(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMember(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除会员编号为"' + sids + '"的数据项？').then(function() {
        return delMember(sids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/member/export', {
        ...this.queryParams
      }, `member_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
