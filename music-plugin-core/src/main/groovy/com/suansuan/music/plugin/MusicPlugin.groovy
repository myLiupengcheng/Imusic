package com.suansuan.music.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * Music 相应的插件入口函数
 */
class MusicPlugin implements Plugin<Project> {

    /**
     * 测试插件
     * @param project
     */
    void apply(Project project) {
        System.out.println("------------------开始----------------------")
        System.out.println("这是我们的自定义插件!")
        System.out.println("------------------结束----------------------->")
    }
}