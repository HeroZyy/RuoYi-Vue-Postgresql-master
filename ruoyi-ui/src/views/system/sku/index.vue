<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="自增主键" prop="sid">
        <el-input
          v-model="queryParams.sid"
          placeholder="请输入自增主键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品sku的id" prop="skuId">
        <el-input
          v-model="queryParams.skuId"
          placeholder="请输入产品sku的id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品sku名称" prop="skuName">
        <el-input
          v-model="queryParams.skuName"
          placeholder="请输入产品sku名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品sku价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入产品sku价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品sku描述" prop="describe">
        <el-input
          v-model="queryParams.describe"
          placeholder="请输入产品sku描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为该产品id下的默认sku" prop="defaultSku">
        <el-input
          v-model="queryParams.defaultSku"
          placeholder="请输入是否为该产品id下的默认sku"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品id" prop="prodId">
        <el-input
          v-model="queryParams.prodId"
          placeholder="请输入产品id"
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
          v-hasPermi="['system:sku:add']"
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
          v-hasPermi="['system:sku:edit']"
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
          v-hasPermi="['system:sku:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:sku:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="skuList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="sid" />
      <el-table-column label="产品sku的id" align="center" prop="skuId" />
      <el-table-column label="产品sku名称" align="center" prop="skuName" />
      <el-table-column label="产品sku价格" align="center" prop="price" />
      <el-table-column label="产品sku描述" align="center" prop="describe" />
      <el-table-column label="是否为该产品id下的默认sku" align="center" prop="defaultSku" />
      <el-table-column label="产品id" align="center" prop="prodId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:sku:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:sku:remove']"
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

    <!-- 添加或修改产品单元对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="自增主键" prop="sid">
          <el-input v-model="form.sid" placeholder="请输入自增主键" />
        </el-form-item>
        <el-form-item label="产品sku的id" prop="skuId">
          <el-input v-model="form.skuId" placeholder="请输入产品sku的id" />
        </el-form-item>
        <el-form-item label="产品sku名称" prop="skuName">
          <el-input v-model="form.skuName" placeholder="请输入产品sku名称" />
        </el-form-item>
        <el-form-item label="产品sku价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入产品sku价格" />
        </el-form-item>
        <el-form-item label="产品sku描述" prop="describe">
          <el-input v-model="form.describe" placeholder="请输入产品sku描述" />
        </el-form-item>
        <el-form-item label="是否为该产品id下的默认sku" prop="defaultSku">
          <el-input v-model="form.defaultSku" placeholder="请输入是否为该产品id下的默认sku" />
        </el-form-item>
        <el-form-item label="产品id" prop="prodId">
          <el-input v-model="form.prodId" placeholder="请输入产品id" />
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
import { listSku, getSku, delSku, addSku, updateSku } from "@/api/system/sku";

export default {
  name: "Sku",
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
      // 产品单元表格数据
      skuList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sid: null,
        skuId: null,
        skuName: null,
        price: null,
        describe: null,
        defaultSku: null,
        prodId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sid: [
          { required: true, message: "自增主键不能为空", trigger: "blur" }
        ],
        skuId: [
          { required: true, message: "产品sku的id不能为空", trigger: "blur" }
        ],
        skuName: [
          { required: true, message: "产品sku名称不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "产品sku价格不能为空", trigger: "blur" }
        ],
        describe: [
          { required: true, message: "产品sku描述不能为空", trigger: "blur" }
        ],
        defaultSku: [
          { required: true, message: "是否为该产品id下的默认sku不能为空", trigger: "blur" }
        ],
        prodId: [
          { required: true, message: "产品id不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询产品单元列表 */
    getList() {
      this.loading = true;
      listSku(this.queryParams).then(response => {
        this.skuList = response.rows;
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
        skuId: null,
        skuName: null,
        price: null,
        describe: null,
        defaultSku: null,
        prodId: null
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
      this.title = "添加产品单元";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sid = row.sid || this.ids
      getSku(sid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改产品单元";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sid != null) {
            updateSku(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSku(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除产品单元编号为"' + sids + '"的数据项？').then(function() {
        return delSku(sids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/sku/export', {
        ...this.queryParams
      }, `sku_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
