-- KPI 模块建表（MySQL 5.7+ / 8.0）
-- 规范：与若依一致的审计字段、软删除（del_flag 0-正常 2-删除）与备注字段

SET NAMES utf8mb4;

-- 指标分类
CREATE TABLE IF NOT EXISTS kpi_indicator_category (
  id               BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  parent_id        BIGINT       DEFAULT 0 COMMENT '父分类ID',
  name             VARCHAR(100) NOT NULL COMMENT '分类名称',
  sort             INT          DEFAULT 0 COMMENT '排序',
  status           CHAR(1)      DEFAULT '0' COMMENT '状态(0正常 1停用)',
  remark           VARCHAR(500) DEFAULT NULL COMMENT '备注',
  create_by        VARCHAR(64)  DEFAULT NULL,
  create_time      DATETIME     DEFAULT CURRENT_TIMESTAMP,
  update_by        VARCHAR(64)  DEFAULT NULL,
  update_time      DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  del_flag         CHAR(1)      DEFAULT '0' COMMENT '删除标志(0代表存在 2代表删除)',
  PRIMARY KEY (id),
  KEY idx_parent (parent_id),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 指标分类';

-- 指标库
CREATE TABLE IF NOT EXISTS kpi_indicator (
  id                 BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
  year               INT           NOT NULL COMMENT '年份',
  kind               VARCHAR(32)   NOT NULL COMMENT '指标种类(dept/person/middle)',
  category_id        BIGINT        NOT NULL COMMENT '分类ID',
  name               VARCHAR(200)  NOT NULL COMMENT '指标名称',
  description        VARCHAR(2000) DEFAULT NULL COMMENT '指标说明',
  scoring_standard   VARCHAR(2000) DEFAULT NULL COMMENT '评分标准',
  source             VARCHAR(64)   DEFAULT NULL COMMENT '评分来源(系统/人工/外部等)',
  score_full         DECIMAL(10,2) NOT NULL DEFAULT 100 COMMENT '考核满分',
  status             CHAR(1)       DEFAULT '0' COMMENT '状态(0正常 1停用)',
  remark             VARCHAR(500)  DEFAULT NULL,
  create_by          VARCHAR(64)   DEFAULT NULL,
  create_time        DATETIME      DEFAULT CURRENT_TIMESTAMP,
  update_by          VARCHAR(64)   DEFAULT NULL,
  update_time        DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  del_flag           CHAR(1)       DEFAULT '0' COMMENT '删除标志(0代表存在 2代表删除)',
  PRIMARY KEY (id),
  KEY idx_year (year),
  KEY idx_kind (kind),
  KEY idx_category (category_id),
  KEY idx_status (status),
  CONSTRAINT fk_indicator_category FOREIGN KEY (category_id) REFERENCES kpi_indicator_category(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 指标库';

-- 指标-适用部门
CREATE TABLE IF NOT EXISTS kpi_indicator_dept (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  indicator_id  BIGINT NOT NULL,
  dept_id       BIGINT NOT NULL COMMENT 'sys_dept.id',
  PRIMARY KEY (id),
  UNIQUE KEY uk_indicator_dept (indicator_id, dept_id),
  KEY idx_indicator (indicator_id),
  KEY idx_dept (dept_id),
  CONSTRAINT fk_ind_dept_indicator FOREIGN KEY (indicator_id) REFERENCES kpi_indicator(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 指标适用部门';

-- 考核周期
CREATE TABLE IF NOT EXISTS kpi_cycle (
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  cycle_type    VARCHAR(32)  NOT NULL COMMENT '周期类型(quarter/half/year/custom)',
  year          INT          NOT NULL,
  start_time    DATETIME     NOT NULL,
  end_time      DATETIME     NOT NULL,
  status        VARCHAR(16)  NOT NULL DEFAULT 'not_started' COMMENT '状态(not_started/ongoing/finished)',
  remark        VARCHAR(500) DEFAULT NULL,
  create_by     VARCHAR(64)  DEFAULT NULL,
  create_time   DATETIME     DEFAULT CURRENT_TIMESTAMP,
  update_by     VARCHAR(64)  DEFAULT NULL,
  update_time   DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  del_flag      CHAR(1)      DEFAULT '0',
  PRIMARY KEY (id),
  KEY idx_cycle (cycle_type, year),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 考核周期';

-- 周期评分权重配置
CREATE TABLE IF NOT EXISTS kpi_cycle_weight (
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  cycle_id      BIGINT       NOT NULL,
  subject_type  VARCHAR(16)  NOT NULL COMMENT 'person/dept/middle',
  rater_post_id BIGINT       DEFAULT NULL COMMENT 'sys_post.id 评分职位，可空表示特定用户',
  weight        DECIMAL(5,2) NOT NULL COMMENT '权重(0-100)',
  PRIMARY KEY (id),
  KEY idx_cycle (cycle_id),
  KEY idx_subject (subject_type),
  CONSTRAINT fk_cycle_weight_cycle FOREIGN KEY (cycle_id) REFERENCES kpi_cycle(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 周期评分权重配置';

-- 个人考核方案
CREATE TABLE IF NOT EXISTS kpi_person_plan (
  id              BIGINT       NOT NULL AUTO_INCREMENT,
  cycle_id        BIGINT       NOT NULL,
  target_user_id  BIGINT       NOT NULL COMMENT '被考核人 sys_user.id',
  target_type     VARCHAR(16)  NOT NULL DEFAULT 'employee' COMMENT 'employee/middle',
  status          VARCHAR(16)  NOT NULL DEFAULT 'draft' COMMENT 'draft/pending/approved/rejected/active/finished',
  submit_time     DATETIME     DEFAULT NULL,
  approve_user_id BIGINT       DEFAULT NULL,
  approve_time    DATETIME     DEFAULT NULL,
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT NULL,
  create_time     DATETIME     DEFAULT CURRENT_TIMESTAMP,
  update_by       VARCHAR(64)  DEFAULT NULL,
  update_time     DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  del_flag        CHAR(1)      DEFAULT '0',
  PRIMARY KEY (id),
  KEY idx_cycle (cycle_id),
  KEY idx_target (target_user_id),
  KEY idx_status (status),
  CONSTRAINT fk_person_plan_cycle FOREIGN KEY (cycle_id) REFERENCES kpi_cycle(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 个人考核方案';

-- 个人方案指标项
CREATE TABLE IF NOT EXISTS kpi_person_plan_item (
  id                BIGINT        NOT NULL AUTO_INCREMENT,
  plan_id           BIGINT        NOT NULL,
  indicator_id      BIGINT        DEFAULT NULL COMMENT '来源指标，可空（纯自定义）',
  indicator_name    VARCHAR(200)  NOT NULL COMMENT '展示名，允许覆盖',
  scoring_standard  VARCHAR(2000) DEFAULT NULL COMMENT '展示标准，允许覆盖',
  score_full        DECIMAL(10,2) NOT NULL DEFAULT 100,
  item_weight       DECIMAL(5,2)  DEFAULT NULL COMMENT '项内权重，可选',
  sort              INT           DEFAULT 0,
  PRIMARY KEY (id),
  KEY idx_plan (plan_id),
  CONSTRAINT fk_person_item_plan FOREIGN KEY (plan_id) REFERENCES kpi_person_plan(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 个人方案指标项';

-- 个人方案-评价关系/权重
CREATE TABLE IF NOT EXISTS kpi_person_plan_rater (
  id             BIGINT       NOT NULL AUTO_INCREMENT,
  plan_id        BIGINT       NOT NULL,
  rater_user_id  BIGINT       DEFAULT NULL COMMENT '特定评价人，可空',
  rater_post_id  BIGINT       DEFAULT NULL COMMENT '评价人职位，可空',
  weight         DECIMAL(5,2) NOT NULL COMMENT '权重(0-100)',
  PRIMARY KEY (id),
  KEY idx_plan (plan_id),
  CONSTRAINT fk_person_rater_plan FOREIGN KEY (plan_id) REFERENCES kpi_person_plan(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 个人方案评价关系';

-- 部门考核方案
CREATE TABLE IF NOT EXISTS kpi_dept_plan (
  id               BIGINT       NOT NULL AUTO_INCREMENT,
  cycle_id         BIGINT       NOT NULL,
  target_dept_id   BIGINT       NOT NULL COMMENT '被考核部门 sys_dept.id',
  status           VARCHAR(16)  NOT NULL DEFAULT 'draft',
  submit_time      DATETIME     DEFAULT NULL,
  approve_user_id  BIGINT       DEFAULT NULL,
  approve_time     DATETIME     DEFAULT NULL,
  remark           VARCHAR(500) DEFAULT NULL,
  create_by        VARCHAR(64)  DEFAULT NULL,
  create_time      DATETIME     DEFAULT CURRENT_TIMESTAMP,
  update_by        VARCHAR(64)  DEFAULT NULL,
  update_time      DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  del_flag         CHAR(1)      DEFAULT '0',
  PRIMARY KEY (id),
  KEY idx_cycle (cycle_id),
  KEY idx_target (target_dept_id),
  KEY idx_status (status),
  CONSTRAINT fk_dept_plan_cycle FOREIGN KEY (cycle_id) REFERENCES kpi_cycle(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 部门考核方案';

-- 部门方案指标项
CREATE TABLE IF NOT EXISTS kpi_dept_plan_item (
  id                BIGINT        NOT NULL AUTO_INCREMENT,
  plan_id           BIGINT        NOT NULL,
  indicator_id      BIGINT        DEFAULT NULL,
  indicator_name    VARCHAR(200)  NOT NULL,
  scoring_standard  VARCHAR(2000) DEFAULT NULL,
  score_full        DECIMAL(10,2) NOT NULL DEFAULT 100,
  item_weight       DECIMAL(5,2)  DEFAULT NULL,
  sort              INT           DEFAULT 0,
  PRIMARY KEY (id),
  KEY idx_plan (plan_id),
  CONSTRAINT fk_dept_item_plan FOREIGN KEY (plan_id) REFERENCES kpi_dept_plan(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 部门方案指标项';

-- 部门方案-评价关系/权重
CREATE TABLE IF NOT EXISTS kpi_dept_plan_rater (
  id             BIGINT       NOT NULL AUTO_INCREMENT,
  plan_id        BIGINT       NOT NULL,
  rater_user_id  BIGINT       DEFAULT NULL,
  rater_post_id  BIGINT       DEFAULT NULL,
  weight         DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (id),
  KEY idx_plan (plan_id),
  CONSTRAINT fk_dept_rater_plan FOREIGN KEY (plan_id) REFERENCES kpi_dept_plan(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 部门方案评价关系';

-- 评价任务
CREATE TABLE IF NOT EXISTS kpi_eval_task (
  id              BIGINT       NOT NULL AUTO_INCREMENT,
  plan_type       VARCHAR(16)  NOT NULL COMMENT 'person/dept',
  plan_id         BIGINT       NOT NULL,
  plan_item_id    BIGINT       NOT NULL,
  target_user_id  BIGINT       DEFAULT NULL,
  target_dept_id  BIGINT       DEFAULT NULL,
  rater_user_id   BIGINT       DEFAULT NULL,
  rater_post_id   BIGINT       DEFAULT NULL,
  deadline        DATETIME     DEFAULT NULL,
  status          VARCHAR(16)  NOT NULL DEFAULT 'pending',
  finish_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (id),
  KEY idx_plan (plan_type, plan_id),
  KEY idx_target_user (target_user_id),
  KEY idx_target_dept (target_dept_id),
  KEY idx_rater (rater_user_id),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 评价任务';

-- 评分（同一任务唯一）
CREATE TABLE IF NOT EXISTS kpi_score (
  id          BIGINT        NOT NULL AUTO_INCREMENT,
  task_id     BIGINT        NOT NULL,
  score       DECIMAL(10,2) NOT NULL,
  comment     VARCHAR(2000) DEFAULT NULL,
  create_by   VARCHAR(64)   DEFAULT NULL,
  create_time DATETIME      DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uk_task (task_id),
  KEY idx_task (task_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 评分';

-- 汇总结果
CREATE TABLE IF NOT EXISTS kpi_result (
  id              BIGINT        NOT NULL AUTO_INCREMENT,
  plan_type       VARCHAR(16)   NOT NULL,
  plan_id         BIGINT        NOT NULL,
  target_user_id  BIGINT        DEFAULT NULL,
  target_dept_id  BIGINT        DEFAULT NULL,
  final_score     DECIMAL(10,2) NOT NULL,
  complete_ratio  DECIMAL(5,2)  DEFAULT NULL COMMENT '完成比例%（任务完成度）',
  status          VARCHAR(16)   NOT NULL DEFAULT 'generated',
  generate_time   DATETIME      DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_plan (plan_type, plan_id),
  KEY idx_target_user (target_user_id),
  KEY idx_target_dept (target_dept_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 结果汇总';

-- 汇总结果明细（按指标项）
CREATE TABLE IF NOT EXISTS kpi_result_item (
  id             BIGINT        NOT NULL AUTO_INCREMENT,
  result_id      BIGINT        NOT NULL,
  plan_item_id   BIGINT        NOT NULL,
  indicator_id   BIGINT        DEFAULT NULL,
  score          DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id),
  KEY idx_result (result_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 结果明细';

-- 结果签字
CREATE TABLE IF NOT EXISTS kpi_result_sign (
  id           BIGINT      NOT NULL AUTO_INCREMENT,
  result_id    BIGINT      NOT NULL,
  user_id      BIGINT      NOT NULL,
  sign_status  CHAR(1)     NOT NULL DEFAULT '0' COMMENT '0未签 1已签',
  sign_time    DATETIME    DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_result_user (result_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KPI 结果签字'; 