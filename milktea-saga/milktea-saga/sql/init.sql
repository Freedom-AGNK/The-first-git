-- ============================================
-- 奶茶物语 · 数据库初始化脚本
-- 适用：MySQL 8.0+
-- 字符集：utf8mb4（支持 emoji，未来想加奶茶 🧋 表情也不慌）
-- ============================================

DROP DATABASE IF EXISTS milktea_saga;
CREATE DATABASE milktea_saga
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE milktea_saga;

-- ============================================
-- 1. admin 店员账号表
-- ============================================
CREATE TABLE admin (
                       id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                       username    VARCHAR(32)  NOT NULL                COMMENT '用户名（唯一）',
                       password    VARCHAR(64)  NOT NULL                COMMENT '密码（明文，仅教学用）',
                       created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店员账号表';

-- ============================================
-- 2. drink 奶茶商品表
-- ============================================
CREATE TABLE drink (
                       id          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                       name        VARCHAR(64)   NOT NULL                COMMENT '奶茶名称',
                       category    VARCHAR(16)   NOT NULL                COMMENT '分类：奶茶/果茶/特调',
                       price       DECIMAL(6,2)  NOT NULL                COMMENT '单价（元）',
                       stock       INT           NOT NULL DEFAULT 0      COMMENT '库存（杯）',
                       is_active   TINYINT(1)    NOT NULL DEFAULT 1      COMMENT '是否在售：1=在售 0=下架',
                       created_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       PRIMARY KEY (id),
                       KEY idx_category (category),
                       KEY idx_is_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='奶茶商品表';

-- ============================================
-- 3. member 会员表
-- ============================================
CREATE TABLE member (
                        id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        name        VARCHAR(32)  NOT NULL                COMMENT '姓名',
                        phone       VARCHAR(11)  NOT NULL                COMMENT '手机号（11位，唯一）',
                        points      INT          NOT NULL DEFAULT 0      COMMENT '会员积分（满1元加1分）',
                        created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入会时间',
                        PRIMARY KEY (id),
                        UNIQUE KEY uk_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员表';

-- ============================================
-- 4. tea_order 订单主表（注意：避开 MySQL 保留字 order）
-- ============================================
CREATE TABLE tea_order (
                           id             BIGINT        NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                           member_id      BIGINT        NULL                    COMMENT '会员ID，散客为NULL',
                           total_amount   DECIMAL(8,2)  NOT NULL                COMMENT '订单总金额',
                           status         VARCHAR(16)   NOT NULL DEFAULT 'PAID' COMMENT '订单状态：PAID=已支付/CANCELLED=已取消',
                           created_at     DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
                           PRIMARY KEY (id),
                           KEY idx_member_id (member_id),
                           KEY idx_created_at (created_at),
                           CONSTRAINT fk_order_member FOREIGN KEY (member_id) REFERENCES member(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';

-- ============================================
-- 5. order_item 订单明细表
-- ============================================
CREATE TABLE order_item (
                            id          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                            order_id    BIGINT        NOT NULL                COMMENT '订单ID',
                            drink_id    BIGINT        NOT NULL                COMMENT '奶茶ID',
                            quantity    INT           NOT NULL                COMMENT '杯数',
                            unit_price  DECIMAL(6,2)  NOT NULL                COMMENT '下单时单价快照（防止后期改价历史失真）',
                            subtotal    DECIMAL(8,2)  NOT NULL                COMMENT '小计 = unit_price * quantity',
                            PRIMARY KEY (id),
                            KEY idx_order_id (order_id),
                            KEY idx_drink_id (drink_id),
                            CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES tea_order(id),
                            CONSTRAINT fk_item_drink FOREIGN KEY (drink_id) REFERENCES drink(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';
