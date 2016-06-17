
// Host Configurations

hostConfigs {
	localhost {
		perfLoadHome = '..' // relative path
		osfamily = 'unix'
		daemonId = 1
		perfmon = true
		startup = [
			[dir: '../refapp', executable: './bin/refapp', args: ['start']]
		]
		shutdown = [
			[dir: '../refapp', executable: './bin/refapp', args: ['stop']]
		]
		archiving {
			gcLogs {
				dir = '../refapp'
				files = 'gclog.txt'
				archiveName = 'gc-logs.zip'
				cleanup = false
			}
			wrapperLog {
				dir = '../refapp'
				files = 'wrapper.log'
				archiveName = 'wrapper-log.zip'
				cleanup = false
			}
			agentLogs {
				dir = '../agent'
				files = '*.log'
				archiveName = 'agent-logs.zip'
				cleanup = false
			}
		}
	}
}