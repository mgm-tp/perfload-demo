
// Host Configurations

hostConfigs {
	localhost {
		perfLoadHome = '..' // relative path
		osfamily = 'unix'
		daemonId = 1
		perfmon = true
		startup = [
			[dir: '../ref-app', executable: './bin/ref-app', args: ['start']]
		]
		shutdown = [
			[dir: '../ref-app', executable: './bin/ref-app', args: ['stop']]
		]
		archiving {
			gcLogs {
				dir = '../ref-app'
				files = 'gclog.txt'
				zipName = 'gc-logs.zip'
				cleanup = false
			}
			wrapperLog {
				dir = '../ref-app'
				files = 'wrapper.log'
				zipName = 'wrapper-log.zip'
				cleanup = false
			}
			agentLogs {
				dir = '../agent'
				files = '*.log'
				zipName = 'agent-logs.zip'
				cleanup = false
			}
		}
	}
}